
//<![CDATA[
$("document").ready(function(){
     
     $("#all").click(function(){   
    if(this.checked){   
        $("input[id='checkbox']").each(function(){this.checked=true;});
        $("#btn1").attr("value","反选");   
    }else{   
        $("input[id='checkbox']").each(function(){this.checked=false;});   
        $("#btn1").attr("value","全选"); 
    }   
 });  
 
      })
//]]>