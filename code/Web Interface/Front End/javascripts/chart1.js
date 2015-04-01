window.onload=init1;
    
	function init1(){    
        
                    var myChart1 = new JSChart('container1', 'bar');
                    myChart1.setDataXML("xml/results1.xml");
                    myChart1.draw();
                    
                   var myChart2 = new JSChart('container2', 'bar');
                   myChart2.setDataXML("xml/results2.xml");
                   myChart2.draw();
                   
                   var myChart3 = new JSChart('container3', 'bar');
                    myChart3.setDataXML("xml/results3.xml");
                    myChart3.draw();
                    
                }
