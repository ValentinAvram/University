				(function() {
				   'use strict';
				   /* jshint browser: true */
				
				   var d=document;
				   var mf=d.getElementById('myform');
				   var se=d.getElementById('second');
				   var lo=d.getElementById('location')
				   var temp;
				
				   mf.reset();
				   se.className='hide';
				   lo.onchange=function() {
				if(this.value==='Temporada' || this.value==='Multiple' ) {
				   se.className=se.className.replace('hide','');
				 }
				else {
				   temp=this.value;
				   se.className='hide';
				   mf.reset();
				   lo.value=temp;
				  }
				 };
				}());