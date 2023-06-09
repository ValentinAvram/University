// Las librerías son dadas por el profesor
#include <opencv2/core/core.hpp>
#include <opencv2/core/utility.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

// Crear una matriz, formato estándar de imagen
Mat imagen;

// Imagen en color (3 channels)
imagen = imread("imagen.jpg", IMREAD_COLOR);

// Imagen en escala de grises (1 channel)
imagen = imread("imagen.jpg", IMREAD_GRAYSCALE);

// Crear una ventana para mostrar la imagen (No es necesario)
namedWindow("Imagen", WINDOW_AUTOSIZE);

// Mostrar la imagen en la ventana (Si existe una ventana creada, debe llamarse igual)
imshow("Imagen", imagen);

// Liberar la memoria de la imagen
imagen.release();

// Liberar la memoria de la ventana
destroyWindow("Imagen");

// Esperar a que se presione una tecla para continuar
waitKey(0);

//Si queremos hacer que se cierre al pulsar ESC:
char key = 0;
while(key != 27)
{
    key = waitKey(0);
}

// Guardar la imagen en un archivo
imwrite("imagen.jpg", imagen);

// Convertir una imagen a escala de grises
cvtColor(imagen, imagen, COLOR_BGR2GRAY);

// Convertir una imagen a color
cvtColor(imagen, imagen, COLOR_GRAY2BGR);

// Convertir una imagen a grayscale pixel by pixel
for(int i = 0; i < image.rows; i++)
{
    for(int j = 0; j < image.cols; j++)
    {
        Vec3b pixel = image.at<Vec3b>(j, i);
        float value = (0.299 * pixel[0]) + (0.587 * pixel[1]) + (0.114 * pixel[2]);
        pixel[0] = value;
        pixel[1] = value;
        pixel[2] = value;
        image.at<Vec3b>(Point(j, i)) = pixel;
    }
}

// Mascaras, son a su vez matrices. Pueden ser de cualquier tamaño o tipo
Mat mascara = Mat::zeros(imagen.size(), imagen.type());

// Callbacks. Reaccionar ante eventos del ratón sobre la imagen.
// Se selecciona la ventana de la imagen y la función a ejecutar sobre esta.
setMouseCallback("Imagen", funcionOnMouse);

// La funcion onMouse tiene una estructura fija:
funcionOnMouse(int event, int x, int y, int flags, void* param)
{
    // Eventos:
    EVENT_LBUTTONDOWN // Click izquierdo
    EVENT_RBUTTONDOWN // Click derecho
    EVENT_MBUTTONDOWN // Click central
    EVENT_MOUSEMOVE // Movimiento del ratón
    EVENT_LBUTTONUP // Soltar click izquierdo
    EVENT_RBUTTONUP // Soltar click derecho
    EVENT_MBUTTONUP // Soltar click central
    EVENT_FLAG_ALTKEY // Tecla Alt pulsada

    // Ejemplo de uso: Siendo X e Y las coordenadas del ratón en el momento del evento
    if(event == EVENT_LBUTTONDOWN)
    {
        Point punto = Point(x, y);
    }
}

// Estrucutra punto
Point punto = Point(x, y);
punto.x // Coordenada X
punto.y // Coordenada Y

// Estrucutra rectángulo
Rect rectangulo = Rect(x, y, width, height);

// Crear una mascara rectangular
rectangle(mascara, rectangulo, Scalar(255, 255, 255), -1);
// O bien
rectangle(mascara, Point1, Point2, Scalar(255, 255, 255), -1);

// Bitwise operations: Sobre todos los pixeles de la imagen
// AND: La mascara tendrá su tamaño X-Y, con el contenido de la imagen en las mismas coordenadas
bitwise_and(mascara, imagen, imagen);

// OR: Combinacion de dos imagenes
bitwise_or(imagen1, imagen2, imagen);

// NOT: Contrario a mask, auxMask tendrá el contorno entero SALVO lo que ocupe mask
bitwise_not(mask, auxMask);

// XOR: Combinacion de dos imagenes, pero solo se toman en cuenta los pixeles que son diferentes
bitwise_xor(imagen1, imagen2, imagen);

// Rango de valores de pixel: [0 - 255].
// Al sumar, restar multiplicar o dividir, se debe tener en cuenta que no se salga de este rango.
// Si se usa float, redondear a int.

// Trackbars: Barra de desplazamiento para modificar valores de parámetros
// Se crea una ventana, se crea la trackbar y se le asigna una función

// valor es el valor actual de la trackbar, que va de 0 a 255 (255 en este caso)
createTrackbar("NombreTrackbar", "NombreVetana", &valor, 255, funcionOnTrackbar);

// La funcion onTrackbar tiene una estructura fija:
funcionOnTrackbar(int valor, void* param)
{
    // Ejemplo de uso: Siendo valor el valor actual de la trackbar
    if(valor == 255)
    {
        // Hacer algo
    }

    // Si se quiere que se actualice la imagen, se debe llamar a la función imshow
}

// Invertir imagen (pixel by pixel)
for(int i = 0; i < image.rows; i++)
{
    for(int j = 0; j < image.cols; j++)
    {
        Vec3b pixel = image.at<Vec3b>(j, i);
        pixel[0] = 255 - pixel[0];
        pixel[1] = 255 - pixel[1];
        pixel[2] = 255 - pixel[2];
        image.at<Vec3b>(Point(j, i)) = pixel;
    }
}

// Blurrear imagen. Siendo size el tamaño del kernel
void blurrImage(Mat& image, int size)
{
    Mat kernel = Mat::ones(size, size, CV_32F) / (float)(size * size);
    filter2D(image, image, -1, kernel, Point(-1, -1), 0, BORDER_DEFAULT);
}

// Unsharp mask
void unsharpMask(Mat& image, Mat& blurredImage, Mat& outputImage, float gValue)
{
    // Convertir imagenes a CV_32FC3
    image.convertTo(image, CV_32FC3);
    blurredImage.convertTo(blurredImage, CV_32FC3);
    outputImage.convertTo(outputImage, CV_32FC3);

    outputImage = (1 + gValue) * image - gValue * blurredImage;

    // Puede ser necesario pasar de nuevo CV_8U
    outputImage.convertTo(outputImage, CV_8U);
}

// Chessboard detection
// Primero, hay que encontrar los corners del tablero

cv::Size patternSize(8, 8); // Tamaño del tablero
vector<Point2f> corners; // Vector de corners
findChessboardCorners(image, patternSize, corners, CALIB_CB_ADAPTIVE_THRESH + CALIB_CB_NORMALIZE_IMAGE);

// Una vez encontrado los puntos de los corners y almacenados en el vector corners, 
// se puede dibujar sobre la imagen

drawChessboardCorners(image, patternSize, corners, true);
imshow("Chessboard", image);

// Proyección de puntos 3D a 2D

cv::Mat proyectarPuntos(Mat& puntos3D, Mat& pryeccion)
{
    cv::Mat puntos2D(puntos3D.rows, 2, CV_32F)

    // Siendo matricial2Homogea una funcion ya dada
    Mat puntos3DHomogeneos = matricial2Homogenea(puntos3D);

    Mat puntos2DHomogeneos = puntos3DHomogeneos * proyeccion.t();

    for(int i = 0; i < puntos2DHomogeneos.rows; i++)
    {
        puntos2D.at<float>(i, 0) = puntos2DHomogeneos.at<float>(i, 0) / puntos2DHomogeneos.at<float>(i, 2);
        puntos2D.at<float>(i, 1) = puntos2DHomogeneos.at<float>(i, 1) / puntos2DHomogeneos.at<float>(i, 2);
    }

    return puntos2D;
}


// Segmentacion de una imagen por diferencia de Frames. Para eliminar background de un video.

Mat previoFram, currentFrame, diferenciaImagenes; // Ya dadas
int threshold = X, int radio = Y; // Ya dados

// Se hace la diferencia entre pevio-current y se guarada en diferenciaImagenes
cv::absdiff(previoFrame, currentFrame, diferenciaImagenes);
// Se convierte a escala de grises
cv::cvtColor(diferenciaImagenes, diferenciaImagenes, COLOR_BGR2GRAY);
// Se aplica un threshold
cv::threshold(diferenciaImagenes, diferenciaImagenes, threshold, 255, THRESH_BINARY);
// Eliminar ruido de una imagen. Mediante Opening y Closing ops.
// El radio ya esta dado.
Mat element = Mat::ones(radio, radio, imagen.type());
morphologyEx(imagen, imagen, MORPH_OPEN, element);
morphologyEx(imagen, imagen, MORPH_CLOSE, element);

// Filtro Gaussiano
GaussianBlur(imagen, imagen, Size(kernel, kernel), 0);

// Cuando se hacen operaciones logaritmicas con el valor de un pixel, se debe tener en cuenta que no sea 0.
// Si es 0, se debe sumar 1.

image.at<uchar>(i,j) = ((log(pixelValue + 1)/log(256)) * 255);

// Fade
image.at<uchar>(y,x)=y % 256;

// Median filter en Gray
void medianFilterGray(Mat &image, Mat &output, int kernelSize)
{
    output = Mat::zeros(image.rows, image.cols, CV_8UC1);
    
    for (int i = 0; i < image.rows; i++)
    {
        for (int j = 0; j < image.cols; j++)
        {
            int median = 0, medianIndex = 0;
            int *medianArray = new int[kernelSize * kernelSize];

            for (int k = 0; k < kernelSize; k++)
            {
                for (int l = 0; l < kernelSize; l++)
                {
                    medianArray[medianIndex] = image.at<uchar>(i + k, j + l);
                    medianIndex++;
                }
            }
    
            for (int k = 0; k < kernelSize * kernelSize; k++)
            {
                for (int l = 0; l < kernelSize * kernelSize - 1; l++)
                {
                    if (medianArray[l] > medianArray[l + 1])
                    {
                        int aux = medianArray[l];
                        medianArray[l] = medianArray[l + 1];
                        medianArray[l + 1] = aux;
                    }
                }
            }
            median = medianArray[(kernelSize * kernelSize) / 2];
            output.at<uchar>(i, j) = median;
        }
    }
}

// Median filter en RGB
void medianFilterColor(Mat &inputImage, Mat &outputImage, int kernelSize)
{
    output = Mat::zeros(image.rows, image.cols, CV_8UC3);
    
    for (int i = 0; i < image.rows; i++)
    {
        for (int j = 0; j < image.cols; j++)
        {
            int medianB=0, medianG=0, medianR=0, medianIndex=0;

            int *medianArrayB = new int[kernelSize * kernelSize];
            int *medianArrayG = new int[kernelSize * kernelSize];
            int *medianArrayR = new int[kernelSize * kernelSize];
            

            for (int k = 0; k < kernelSize; k++)
            {
                for (int l = 0; l < kernelSize; l++)
                {
                    medianArrayB[medianIndex] = image.at<Vec3b>(i + k, j + l)[0];
                    medianArrayG[medianIndex] = image.at<Vec3b>(i + k, j + l)[1];
                    medianArrayR[medianIndex] = image.at<Vec3b>(i + k, j + l)[2];                 
                    medianIndex++;
                }
            }
    
            for (int k = 0; k < kernelSize * kernelSize; k++)
            {
                for (int l = 0; l < kernelSize * kernelSize - 1; l++)
                {
                    if (medianArrayB[l] > medianArrayB[l + 1])
                    {
                        int aux = medianArrayB[l];
                        medianArrayB[l] = medianArrayB[l + 1];
                        medianArrayB[l + 1] = aux;
                    }
                    if (medianArrayG[l] > medianArrayG[l + 1])
                    {
                        int aux = medianArrayG[l];
                        medianArrayG[l] = medianArrayG[l + 1];
                        medianArrayG[l + 1] = aux;
                    }
                    if (medianArrayR[l] > medianArrayR[l + 1])
                    {
                        int aux = medianArrayR[l];
                        medianArrayR[l] = medianArrayR[l + 1];
                        medianArrayR[l + 1] = aux;
                    }
                }
            }
            
            medianB = medianArrayB[(kernelSize * kernelSize) / 2];
            medianG = medianArrayG[(kernelSize * kernelSize) / 2];
            medianR = medianArrayR[(kernelSize * kernelSize) / 2];
            
            output.at<Vec3b>(i, j)[0] = medianB;
            output.at<Vec3b>(i, j)[1] = medianG;
            output.at<Vec3b>(i, j)[2] = medianR;     
        }
    }
}

// Gaussian blur a mano
void gaussBlur(Mat imagen, Mat output, Mat kernel)
{
    for(int i = 0; i < imagen.rows; i++)
    {
        for(int j = 0; j < imagen.cols; j++)
        {
            float p = 0;

            for(int k = 0; k < kernel.rows; k++)
            {
                for(int l = 0; l < kernel.cols; l++)
                {
                    p += imagen.at<uchar>(i + k, j + l) * kernel.at<float>(k, l);
                }
            }
        }
    }

    if(i + kernel.rows/2 < imagen.rows && j + kernel.cols/2 < imagen.cols)
    {
        output.at<uchar>(i + kernel.rows/2, j + kernel.cols/2) = p / sum(kernel)[0];
    }
}

// Sobel
void sobel(Mat imagen, Mat output)
{
    Mat kernelX = (Mat_<float>(3,3) << -1, 0, 1, -2, 0, 2, -1, 0, 1);
    Mat kernelY = (Mat_<float>(3,3) << -1, -2, -1, 0, 0, 0, 1, 2, 1);

    Mat imagenX = Mat::zeros(imagen.rows, imagen.cols, CV_8UC1);
    Mat imagenY = Mat::zeros(imagen.rows, imagen.cols, CV_8UC1);

    gaussBlur(imagen, imagenX, kernelX);
    gaussBlur(imagen, imagenY, kernelY);

    for(int i = 0; i < imagen.rows; i++)
    {
        for(int j = 0; j < imagen.cols; j++)
        {
            output.at<uchar>(i, j) = sqrt(pow(imagenX.at<uchar>(i, j), 2) + pow(imagenY.at<uchar>(i, j), 2));
        }
    }
}

// Normalizar. Puede ser de 0 a 1, o de 0 a 255, o de 0 a N, etc.
cv::normalize(image, image, 0, 1, cv::NORM_MINMAX, -1);

// Histograma by hand
vector<int> calculateHistogram(Mat &input)
{
	vector<int> histograma(256,0);

    for(int i=0; i<input.rows; i++)
    {
        uchar *ptr = input.ptr<uchar>(i);

        for(int j=0; j<input.cols; j++)
        {
            histograma[ptr[j]] += 1;
        }
    }
	return histograma;
}



// Histograma con OpenCV
void calculateHistogram(Mat &input, Mat &output)
{
    int channels[] = {0};
    int histSize[] = {256};
    float range[] = {0, 256};
    const float *ranges[] = {range};

    calcHist(&input, 1, channels, Mat(), output, 1, histSize, ranges);
}

// Cumulative histogram
vector<int> cumulativeHistogram(vector<int> histogram)
{
    vector<int> cumulativeHistogram(256,0);

    cumulativeHistogram[0] = histogram[0];

    for(int i=1; i<256; i++)
    {
        cumulativeHistogram[i] = cumulativeHistogram[i-1] + histogram[i];
    }

    return cumulativeHistogram;
}

// Robert filter
Mat robertFilter(Mat image)
{
    Mat output = Mat::zeros(image.rows, image.cols, CV_8UC1);
    
    for (int i = 0; i < image.rows; i++)
    {
        for (int j = 0; j < image.cols; j++)
        {
            int gx = image.at<uchar>(i, j) - image.at<uchar>(i + 1, j + 1);
            int gy = image.at<uchar>(i + 1, j) - image.at<uchar>(i, j + 1);
            
            int g = sqrt(gx * gx + gy * gy);
            
            output.at<uchar>(i, j) = g;
        }
    } 
    return output;
}


// Operación convolucional
void convolution(const Mat & imagen, int kernelSize)
{
    Mat output(imagen.rows, imagen.cols, imagen.type());
    Mat kernel(kernelSize, kernelSize,CV_32F);

    for(int i = 0; i < kernel.rows; i++)
    {
		for(int j = 0; j < kernel.cols; j++)
        {
			kernel.at<float>(i,j) = 3;
        }
    }

    imagen.copyTo(output);
    float sum;

    for(int y = 1; y < imagen.rows-1; y++)
    {
      for(int x = 1; x < imagen.cols-1; x++)
      {
        sum=0.0;
        for(int k = -1; k <= 1; k++)
        {
          for(int j = -1; j <= 1; j++)
          {
            sum = sum + kernel.at<uchar>() * imagen.at<uchar>(y-j,x-k);
          }
        }
        output.at<uchar>(y, x)=sum;
      }
    }

    imshow("Output",dst);
}

// Capturar video
VideoCapture cap("video.mp4");

// Cerrar video
cap.release();

// Mostrar video
while(1)
{   
    // Se muestra frame a frame
    Mat frame;
    cap >> frame;
    imshow("Video", frame);
}

// Canny edge. Bordes en videos
void cannyEdge(VideoCapture video)
{
    while(1)
    {
        Mat blackBackground(480,640,CV_8UC3,Scalar(0,0,0));
        Mat edges;
        //Read the video frame
        video >> frame;

        //If the frame is empty, break immediately
        if(frame.empty())
            break;

        //Convert the frame to gray color
        cvtColor(frame, frame, COLOR_BGR2GRAY);

        //Apply the Canny edge detector
        Canny(frame, edges, 50, 150, 3);

        //Draw the edges on the black background
        frame.copyTo(blackBackground,edges);

        imshow("Video",frame);
        imshow("Edges",edges);

        waitKey(30);
    }
}