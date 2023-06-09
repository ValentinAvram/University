#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
Created on Sun Oct 25 12:37:04 2020

IMC: lab assignment 3

@author: pagutierrez

How to debug in Spyder: 
debugfile('rbf.py', wdir='/home/javi/Documentos/uco/docencia/docencia_2022-2023/IMC/Practicas/imc2223/spyder-workspace/practica3', args='-t train_ildp.csv -T test_ildp.csv -c --l2')

debugfile('rbf.py', wdir='/home/javi/Documentos/uco/docencia/docencia_2022-2023/IMC/Practicas/imc2223/spyder-workspace/practica3', args='-t train_ildp.csv -T test_ildp.csv -c --l2 -f')
"""
import pandas as pd
import numpy as np
import click
import pickle
import os
from sklearn.cluster import KMeans
from sklearn.linear_model import LogisticRegression
from numpy.linalg import inv
from numpy.linalg import pinv
from scipy.spatial.distance import pdist, squareform
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error
#from sklearn import preprocessing

@click.command()
@click.option('--train_file', '-t', default=None, required=False,
              help=u'Name of the file with training data.')
@click.option('--test_file', '-T', default=None, required=True,
              help=u'Name of the file with test data.')
@click.option('--classification', '-c', is_flag=True, default=False, show_default=True,
              help=u'The problem considered is a classification problem.')
@click.option('--ratio_rbf', '-r', default=0.10, show_default=True,
              help=u'Ratio of RBF neurons (as a fraction of 1) with respect to the total number of patterns.')
@click.option('--l2', '-l', is_flag=True, default=False, show_default=True,
              help=u'Use L2 regularization instead of L1 (logistic regression).')
@click.option('--eta', '-e', default=1e-2, show_default=True,
              help=u'Value of the regularization parameter for logistic regression.')
@click.option('--fairness', '-f', is_flag=True, default=False, show_default=True,
              help=u'Evaluates prediction using fairlern metrics. It is assumed that last input variable is the group variable.')
@click.option('--outputs', '-o', default=1, show_default=True,
              help=u'Number of columns that will be used as target variables (all at the end).')
@click.option('--pred', '-p', is_flag=True, default=False, show_default=True,
              help=u'Use the prediction mode.') # KAGGLE
@click.option('--model', '-m', default="", show_default=False,
              help=u'Directory name to save the models (or name of the file to load the model, if the prediction mode is active).') # KAGGLE

def train_rbf_total(train_file, test_file, classification, ratio_rbf, l2, eta, fairness, outputs, model, pred):
    """ 5 executions of RBFNN training
    
        RBF neural network based on hybrid supervised/unsupervised training.
        We run 5 executions with different seeds.
    """

    if not pred:    

        if train_file is None:
            print("You have not specified the training file (-t)")
            return

        train_mses = np.empty(5)
        train_ccrs = np.empty(5)
        test_mses = np.empty(5)
        test_ccrs = np.empty(5)
        
        if fairness:
            train_fn0 = np.empty(5)
            train_fn1 = np.empty(5)
            test_fn0 = np.empty(5)
            test_fn1 = np.empty(5)
    
        for s in range(1,6,1):
            print("-----------")
            print("Seed: %d" % s)
            print("-----------")
            np.random.seed(s)
 
            train_results, test_results = \
                train_rbf(train_file, test_file, classification, ratio_rbf, l2, eta, fairness, outputs, \
                             model and "{}/{}.pickle".format(model, s) or "")
 
            train_mses[s-1] = train_results["mse"]
            test_mses[s-1] = test_results["mse"] 
            train_ccrs[s-1] = train_results["ccr"]
            test_ccrs[s-1] = test_results["ccr"]
            
            print("Training MSE: %f" % train_mses[s-1])
            print("Test MSE: %f" % test_mses[s-1])
            print("Training CCR: %.2f%%" % train_ccrs[s-1])
            print("Test CCR: %.2f%%" % test_ccrs[s-1])
            
            if fairness:
                #print("Training Overall Rates:  ")
                #print(train_results["fairnes_metrics"].overall)
                #print("FN Rate by groups = ", train_results["fairnes_metrics"].by_group.to_dict())
                #print("Test Overall Rates:  ")
                #print(test_results["fairnes_metrics"].overall)
                #print("FN Rate by groups = ", test_results["fairnes_metrics"].by_group.to_dict())
                
                train_fn0[s-1] = train_results["fairnes_metrics"].by_group.to_dict()['false negative rate'][0.0] * 100
                train_fn1[s-1] = train_results["fairnes_metrics"].by_group.to_dict()['false negative rate'][1.0] * 100
                test_fn0[s-1] = test_results["fairnes_metrics"].by_group.to_dict()['false negative rate'][0.0] * 100
                test_fn1[s-1] = test_results["fairnes_metrics"].by_group.to_dict()['false negative rate'][1.0] * 100

        
        print("******************")
        print("Summary of results")
        print("******************")
        print("Training MSE: %f +- %f" % (np.mean(train_mses), np.std(train_mses)))
        print("Test MSE: %f +- %f" % (np.mean(test_mses), np.std(test_mses)))
        print("Training CCR: %.2f%% +- %.2f%%" % (np.mean(train_ccrs), np.std(train_ccrs)))
        print("Test CCR: %.2f%% +- %.2f%%" % (np.mean(test_ccrs), np.std(test_ccrs)))
        if fairness: 
            print("Training FN0: %.2f%% +- %.2f%%" % (np.mean(train_fn0), np.std(train_fn0)))
            print("Training FN1: %.2f%% +- %.2f%%" % (np.mean(train_fn1), np.std(train_fn1)))
            print("Test FN0: %.2f%% +- %.2f%%" % (np.mean(test_fn0), np.std(test_fn0)))
            print("Test FN1: %.2f%% +- %.2f%%" % (np.mean(test_fn1), np.std(test_fn1)))
        
            
    else:
        # KAGGLE
        if model is None:
            print("You have not specified the file with the model (-m).")
            return

        # Obtain the predictions for the test set
        predictions = predict(test_file, model)

        # Print the predictions in csv format
        print("Id,Category")
        for prediction, index in zip(predictions, range(len(predictions))):
            s = ""            
            s += str(index)
            
            if isinstance(prediction, np.ndarray):
                for output in prediction:
                    s += ",{}".format(output)
            else:
                s += ",{}".format(int(prediction))
                
            print(s)
            


def train_rbf(train_file, test_file, classification, ratio_rbf, l2, eta, fairness, outputs, model_file=""):
    """ One execution of RBFNN training
    
        RBF neural network based on hybrid supervised/unsupervised training.
        We run 1 executions.

        Parameters
        ----------
        train_file: string
            Name of the training file
        test_file: string
            Name of the test file
        classification: bool
            True if it is a classification problem
        ratio_rbf: float
            Ratio (as a fraction of 1) indicating the number of RBFs
            with respect to the total number of patterns
        l2: bool
            True if we want to use L2 regularization for logistic regression 
            False if we want to use L1 regularization for logistic regression
        eta: float
            Value of the regularization factor for logistic regression
        fairness: 
            False. If set to true, it will calculate fairness metrics on the prediction
        outputs: int
            Number of variables that will be used as outputs (all at the end
            of the matrix)
        model_file: string
            Name of the file where the model will be written

        Returns
        -------
        train_results: dict
            Dictionary with each metric results for training data: 'ccr' (float), 
            'mse' (float), 'fairnes_metrics' (fairlearn metrics structure). For 
            regression ccr will be zero and for classification mse will be zero.
            Fairness metrics will be available only if fairness=True is provided. 
        
        test_results: dict
            Dictionary with each metric results for testing data. Equivalent to 
            train_results but for the test set. 

    """
    train_inputs, train_outputs, test_inputs, test_outputs = read_data(train_file, 
                                                                        test_file,
                                                                        outputs)

    num_rbf = int(ratio_rbf * train_outputs.shape[0])
    print("Number of RBFs used: %d" %(num_rbf))
    # 1. Init centroids + 2. clustering 
    kmeans, distances, centers = clustering(classification, train_inputs, 
                                              train_outputs, num_rbf)
    
    # 3. Adjust radii
    radii = calculate_radii(centers, num_rbf)
    
    # 4. R matrix 
    r_matrix = calculate_r_matrix(distances, radii)

    # 5. Calculate betas
    if not classification:
        coefficients = invert_matrix_regression(r_matrix, train_outputs)
    else:
        logreg = logreg_classification(r_matrix, train_outputs, l2, eta)

    # Calculate distances to centroids + R matriz
    test_distances = kmeans.transform(test_inputs)    
    test_r_matrix = calculate_r_matrix(test_distances, radii)
    
    # # # # KAGGLE # # # #
    if model_file != "":
        save_obj = {
            'classification' : classification,            
            'radii' : radii,
            'kmeans' : kmeans
        }
        if not classification:
            save_obj['coefficients'] = coefficients
        else:
            save_obj['logreg'] = logreg

        dir = os.path.dirname(model_file)
        if not os.path.isdir(dir):
            os.makedirs(dir)

        with open(model_file, 'wb') as f:
            pickle.dump(save_obj, f)
    
    # # # # # # # # # # #

    if not classification:
        train_predictions = np.dot(r_matrix, coefficients)
        test_predictions = np.dot(test_r_matrix, coefficients)
        #print np.array_str(test_predictions, suppress_small=True)
        train_mse = sum(sum((train_outputs - train_predictions) ** 2)) / \
                    (outputs*train_predictions.shape[0])
        test_mse = sum(sum((test_outputs - test_predictions) ** 2)) / \
                   (outputs*test_predictions.shape[0])
        if outputs == 1:
            train_ccr = sum(np.around(train_predictions) == train_outputs) / \
                        float(len(train_predictions)) * 100
            test_ccr = sum(np.around(test_predictions) == test_outputs) / \
                       float(len(test_predictions)) * 100
        else:
            train_ccr = 0
            test_ccr = 0
    else:
        train_predictions = logreg.predict(r_matrix)
        test_predictions = logreg.predict(test_r_matrix)
        train_0_1 = (train_outputs == range(0,int(max(train_outputs)[0]+1)))
        test_0_1 = (test_outputs == range(0,int(max(test_outputs)[0]+1)))
        train_mse = sum(sum((train_0_1 - logreg.predict_proba(r_matrix))**2)) / \
                    ((max(train_outputs)+1)*train_outputs.shape[0])
        train_mse = sum(train_mse)
        test_mse = sum(sum((test_0_1 - logreg.predict_proba(test_r_matrix))**2)) / \
                    ((max(test_outputs)+1)*test_outputs.shape[0])
        test_mse = sum(test_mse)
        train_ccr = sum(train_predictions == train_outputs.ravel()) / \
                    float(len(train_predictions)) * 100
        test_ccr = sum(test_predictions == test_outputs.ravel()) / \
                   float(len(test_predictions)) * 100	
        #np.set_printoptions( threshold=None, edgeitems=None, linewidth=None, \
        #           formatter = dict( float = lambda x: "%.3g" % x ))  # float arrays %.3g	
        #print(np.array_str(logreg.predict_proba(test_r), suppress_small=True))
        
        train_results = {
            'ccr' : train_ccr,
            'mse' : train_mse}
        
        test_results = {
            'ccr' : test_ccr,
            'mse' : test_mse}
        
        # Fairness evaluation
        if fairness:
            # Group label (we assume it is in the last column of input data): 
            train_gender_bin = np.floor(train_inputs[:,-1])
            test_gender_bin = np.floor(test_inputs[:,-1])
            # 1 women / 0 men
            train_gender_bin[train_gender_bin == -1] = 0
            test_gender_bin[test_gender_bin == -1] = 0
            
            from fairlearn.metrics import MetricFrame
            from fairlearn.metrics import false_negative_rate
            from fairlearn.metrics import false_positive_rate
            
            metrics = {
                'false negative rate': false_negative_rate,
                'false positive rate': false_positive_rate}


            train_fm = MetricFrame(metrics=metrics,
                                         y_true=train_outputs,
                                         y_pred=train_predictions,
                                         sensitive_features=train_gender_bin)
            
            train_results['fairnes_metrics'] = train_fm


            test_fm = MetricFrame(metrics=metrics,
                                         y_true=test_outputs,
                                         y_pred=test_predictions,
                                         sensitive_features=test_gender_bin)
            
            test_results['fairnes_metrics'] = test_fm
            
            return train_results, test_results
        
        return train_results, test_results 

    
    
def read_data(train_file, test_file, outputs):
    """ Read the input data
        It receives the name of the input data file names (training and test)
        and returns the corresponding matrices

        Parameters
        ----------
        train_file: string
            Name of the training file
        test_file: string
            Name of the test file
        outputs: int
            Number of variables to be used as outputs
            (all at the end of the matrix).
              
        Returns
        -------
        train_inputs: array, shape (n_train_patterns,n_inputs)
            Matrix containing the inputs for the training patterns
        train_outputs: array, shape (n_train_patterns,n_outputs)
            Matrix containing the outputs for the training patterns
        test_inputs: array, shape (n_test_patterns,n_inputs)
            Matrix containing the inputs for the test patterns
        test_outputs: array, shape (n_test_patterns,n_outputs)
            Matrix containing the outputs for the test patterns
    """
    train_df = pd.read_csv(train_file, header=None)
    test_df = pd.read_csv(test_file, header=None)
    train_inputs = train_df.values[:, :(-outputs)]
    train_outputs = train_df.values[:, (-outputs):]
    test_inputs = test_df.values[:, :(-outputs)]
    test_outputs = test_df.values[:, (-outputs):]

#    min_max_scaler = preprocessing.MinMaxScaler(feature_range=(-1, 1))
#    train_inputs = min_max_scaler.fit_transform(train_inputs)
#    test_inputs = min_max_scaler.fit_transform(test_inputs)
#    train_df.ix[:, 0:(train_df.shape[1] - 2)] = train_inputs
#    test_df.ix[:, 0:(test_df.shape[1] - 2)] = test_inputs
#    train_df.to_csv('1',header=False,index=False)
#    test_df.to_csv('2',header=False,index=False)
    return train_inputs, train_outputs, test_inputs, test_outputs

def init_centroids_classification(train_inputs, train_outputs, num_rbf):
    """ Initialize the centroids for the case of classification
        This method selects in a stratified num_rbf patterns.

        Parameters
        ----------
        train_inputs: array, shape (n_patterns,n_inputs)
            Matrix with all the input variables
        train_outputs: array, shape (n_patterns,n_outputs)
            Matrix with the outputs of the dataset
        num_rbf: int
            Number of RBFs to be used in the network
            
        Returns
        -------
        centroids: array, shape (num_rbf,n_inputs)
            Matrix with all the centroids already selected
    """
    
    """
    Versión complicada
    """
    """
    num_clases = max(train_outputs) + 1
    num_por_clase = num_rbf / num_clases
    clase0 = train_inputs.ix[train_outputs == 0]
    centroids = clase0.ix[clase0.index[np.random.randint(clase0.shape[0],
                                                          size=num_por_clase)],
                     :]
    for i in range(1, num_clases):
        clase_i = train_inputs.ix[train_outputs == i, :]
        if i == (num_clases - 1):
            cuantos_i = num_rbf - (num_por_clase * (num_clases - 1))
        else:
            cuantos_i = num_por_clase
        centroids = np.concatenate((centroides,clase_i.ix[clase_i.index[
                        np.random.randint(clase_i.shape[0],size=cuantos_i)],
                                         :]),axis=0)
    """
    
    train_X, centroids, train_y, test_y = train_test_split(train_inputs, train_outputs.ravel(),                                                     test_size=num_rbf,                                                   stratify=train_outputs)

    """
    sss = StratifiedShuffleSplit(train_outputs, 1, test_size=num_rbf)
    for train_index, test_index in sss:
        centroids = train_inputs.values[test_index, :]
    """

    return centroids

def clustering(classification, train_inputs, train_outputs, num_rbf):
    """ It applies the clustering process
        A clustering process is applied to set the centers of the RBFs.
        In the case of classification, the initial centroids are set
        using the method init_centroids_classification(). 
        In the case of regression, the centroids have to be set randomly.

        Parameters
        ----------
        classification: bool
            True if it is a classification problem
        train_inputs: array, shape (n_patterns,n_inputs)
            Matrix with all the input variables
        train_outputs: array, shape (n_patterns,n_outputs)
            Matrix with the outputs of the dataset
        num_rbf: int
            Number of RBFs to be used in the network
            
        Returns
        -------
        kmeans: sklearn.cluster.KMeans
            KMeans object after the clustering
        distances: array, shape (n_patterns,num_rbf)
            Matrix with the distance from each pattern to each RBF center
        centers: array, shape (num_rbf,n_inputs)
            Centers after the clustering
    """
    if not classification:
        kmeans = KMeans(init='random', n_clusters=num_rbf, n_init=1,
                        max_iter=500)
    else:
        centroids = init_centroids_classification(train_inputs, train_outputs, num_rbf)
        kmeans = KMeans(init=centroids, n_clusters=num_rbf, n_init=1,
                        max_iter=500)
    distances = kmeans.fit_transform(train_inputs)
    centers = kmeans.cluster_centers_
    return kmeans, distances, centers

def calculate_radii(centers, num_rbf):
    """ It obtains the value of the radii after clustering
        This methods is used to heuristically obtain the radii of the RBFs
        based on the centers

        Parameters
        ----------
        centers: array, shape (num_rbf,n_inputs)
            Centers from which obtain the radii
        num_rbf: int
            Number of RBFs to be used in the network
            
        Returns
        -------
        radii: array, shape (num_rbf,)
            Array with the radius of each RBF
    """
    center_distances = squareform(pdist(centers, 'euclidean'))
    radii = sum(center_distances) / (2.0 * (num_rbf - 1))
    return radii

def calculate_r_matrix(distances, radii):
    """ It obtains the R matrix
        This method obtains the R matrix (as explained in the slides),
        which contains the activation of each RBF for each pattern, including
        a final column with ones, to simulate bias
        
        Parameters
        ----------
        distances: array, shape (n_patterns,num_rbf)
            Matrix with the distance from each pattern to each RBF center
        radii: array, shape (num_rbf,)
            Array with the radius of each RBF
            
        Returns
        -------
        r_matrix: array, shape (n_patterns,num_rbf+1)
            Matrix with the activation of every RBF for every pattern. Moreover
            we include a last column with ones, which is going to act as bias
    """
    # R = exp(-((distances**2)/(2*(radio**2))))
    r_matrix = np.exp(-((distances ** 2) / (2 * (np.tile(radii,(distances.shape[0],1)) ** 2))))
    r_matrix = np.concatenate((np.ones((distances.shape[0], 1)), r_matrix), axis=1)
    return r_matrix

def invert_matrix_regression(r_matrix, train_outputs):
    """ Inversion of the matrix for regression case
        This method obtains the pseudoinverse of the r matrix and multiplies
        it by the targets to obtain the coefficients in the case of linear
        regression
        
        Parameters
        ----------
        r_matrix: array, shape (n_patterns,num_rbf+1)
            Matrix with the activation of every RBF for every pattern. Moreover
            we include a last column with ones, which is going to act as bias
        train_outputs: array, shape (n_patterns,n_outputs)
            Matrix with the outputs of the dataset
              
        Returns
        -------
        coefficients: array, shape (n_outputs,num_rbf+1)
            For every output, values of the coefficients for each RBF and value 
            of the bias 
    """
    pseudoinverse = np.dot(pinv(np.dot(r_matrix.transpose(), r_matrix)),
                           r_matrix.transpose())
    #pseudoinverse = pinv(r_matrix,rcond=1e-7)
    coefficients = np.dot(pseudoinverse, train_outputs)
    return coefficients

def logreg_classification(matriz_r, train_outputs, l2, eta):
    """ Performs logistic regression training for the classification case
        It trains a logistic regression object to perform classification based
        on the R matrix (activations of the RBFs together with the bias)
        
        Parameters
        ----------
        r_matrix: array, shape (n_patterns,num_rbf+1)
            Matrix with the activation of every RBF for every pattern. Moreover
            we include a last column with ones, which is going to act as bias
        train_outputs: array, shape (n_patterns,n_outputs)
            Matrix with the outputs of the dataset
        l2: bool
            True if we want to use L2 regularization for logistic regression 
            False if we want to use L1 regularization for logistic regression
        eta: float
            Value of the regularization factor for logistic regression
              
        Returns
        -------
        logreg: sklearn.linear_model.LogisticRegression
            Scikit-learn logistic regression model already trained
    """
    if l2:
        logreg = LogisticRegression(solver='liblinear',C=(1.0 / eta), penalty='l2', 
                                    fit_intercept=False)
    else:
        logreg = LogisticRegression(solver='liblinear',C=(1.0 / eta), penalty='l1', 
                                    fit_intercept=False)
    logreg.fit(matriz_r, train_outputs.ravel())
    #print(np.sum(logreg.coef_<0.0001))
    return logreg


def predict(test_file, model_file):
    """ Performs a prediction with RBFNN model
        It obtains the predictions of a RBFNN model for a test file, using two files, one
        with the test data and one with the model

        Parameters
        ----------
        test_file: string
            Name of the test file
        model_file: string
            Name of the file containing the model data

        Returns
        -------
        test_predictions: array, shape (n_test_patterns,n_outputs)
            Predictions obtained with the model and the test file inputs
    """
    test_df = pd.read_csv(test_file, header=None)
    test_inputs = test_df.values[:, :]
    
    with open(model_file, 'rb') as f:
        saved_data = pickle.load(f)
    
    radii = saved_data['radii']
    classification = saved_data['classification']
    kmeans = saved_data['kmeans']
    
    test_distancias = kmeans.transform(test_inputs)    
    test_r = calculate_r_matrix(test_distancias, radii)    
    
    if classification:
        logreg = saved_data['logreg']
        test_predictions = logreg.predict(test_r)
    else:
        coeficientes = saved_data['coefficients']
        test_predictions = np.dot(test_r, coeficientes)
        
    return test_predictions
    
if __name__ == "__main__":
    train_rbf_total()
