window.onload=init2;
    
	function init2(){    
        
                    var myChart2 = new JSChart('container2', 'bar');
                    myChart2.setDataXML("xml/results2.xml");
                    myChart2.draw();
                }
