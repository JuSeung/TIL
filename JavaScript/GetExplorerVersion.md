##Explorer Version  
	 - 웹 개발을 하다보면  Explorer 상위 버전에서는 되는대 하위 버전에는 안되는 경우가 있다.
	 - 하위 버전에도 똑같은 기능을 만들려면 처음 부터 하위버전에서도 돌아가는 함수를 이용하면 될 것이고 만약에 하위 버전에서는 되는 함수가 상위 버전에서는 지원을 안하는 경우가 있다. 그래서 유용하게
	      요구사항이 익스플로우 버전에 따라 기능을 개발 해줘야 할때 유용하게 쓰일 수 있다고 생각이 든다.
	   
	  
	    
```
    function cfGetExplorerVersion(){  
		 var word; 
		 var version = "N/A"; 
		 var agent = navigator.userAgent.toLowerCase(); 
		 var name = navigator.appName; 
		 
		 // IE 10 or Lower  
		 if ( name == "Microsoft Internet Explorer" ){ 
			 word = "msie "; 
		 } else { 
			 // IE 11 
			 if ( agent.search("trident") > -1 ) word = "trident/.*rv:"; 
			 // Microsoft Edge 
			 else if ( agent.search("edge/") > -1 ) word = "edge/"; 
		 } 
		 var reg = new RegExp( word + "([0-9]{1,})(\\.{0,}[0-9]{0,1})" ); 
		 if (  reg.exec( agent ) != null  ) {
			version = RegExp.$1; 
		 }
		 return version; 
	}
```
	

 - 원본 : <http://stackoverflow.com/questions/19999388/jquery-check-if-user-is-using-ie>
 - 수정본 : <http://tonks.tistory.com/107>
	
  	