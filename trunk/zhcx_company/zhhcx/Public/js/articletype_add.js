    $(document).ready(function(){
    
	    //找到所有的td节点
	    var tds = $(".doubleclick");
	    //给所有的td添加事件
	    tds.click(tdClick);
        
    });
    
	//td的click事件 由td状态变为input可输入状态
	function tdClick() {
	
	    var td = $(this);
	    //1 保存td中的文本内容
	    var text = td.text();
	    //2 清空td中的内容 td.empty()
	    td.html("");
	    //3 建立input标签
	    var input = $("<input>");
	    //4 设定input标签的值
	    input.attr("value", text);
		input.addClass("inpback");
	    //5 添加input的事件 键盘处理(回车确认) 失去光标
	    input.keyup(function(event) {
	        //取得按下的键
	        //解决IE与其他浏览器差异 IE时event为undefine 使用window.event
	        var myEvent = event || window.event;
	        var keyvalue = myEvent.keyCode;
	        //判断是否是回车按下
	        if (keyvalue == 13) {
	            // reTdClick(this);
	            var inputNode = $(this);
	            //保存文本内容
	            var inputText = inputNode.val();
	            //对td重新赋值
	            var tdNode = inputNode.parent();
	            tdNode.html(inputText);
	            //让td重新设定onclick事件
	            tdNode.click(tdClick);
	        }
	        //判断是否Esc按下
	        if(keyvalue == 27){
	            var inputNode = $(this);
	            //对td重新赋值
	            var tdNode = inputNode.parent();
	            tdNode.html(text);
	            //让td重新设定onclick事件
	            tdNode.click(tdClick);
	        }
	    });
	    //input注册onblur事件 失去焦点自动保存 恢复成为td状态
	    input.blur(reTdClick);
	    //6 把input加入到td里面 input.appendTo(td)
	    td.append(input);
	    //7 移除td的onclick事件
	    td.unbind("click");
	    //设定光标 全选
	    var inputdom = input.get(0);
	    inputdom.select();
	    inputdom.focus();
	}

		//状态还原 td状态
	function reTdClick(){
		var inputNode = $(this);
		//保存文本内容
		var inputText = inputNode.val();
		//对td重新赋值
		var tdNode = inputNode.parent();
		tdNode.html(inputText);
		if(tdNode.prev().prev().html()==null){
			id = tdNode.prev().html();
			name = tdNode.html();
			url = tdNode.next().html();
		}else{
			id = tdNode.prev().prev().html();
			name = tdNode.prev().html();
		}
		$.ajax({
		   type: "POST",
		   url: "editArticletype",
		   data: 'id='+id+'&name='+name,
		   dataType:"json",
		   success: function(msg){
		     
		   }
		});
		//让td重新设定onclick事件
		tdNode.click(tdClick);
	}

$(function(){
	$("#addatp").button();
	
	$("#addatp").click(function(){
		$("#adddiv").dialog({
			modal: true,
			buttons: {
				上传: function() {
					$.ajax({
						   type: "POST",
						   url: "insertArticletype",
						   data: 'name='+$("#addname").val()+'&nav='+$("#addnav").val(),
						   dataType:"json",
						   success: function(msg){
						     
						   }
						});
					$( this ).dialog( "close" );
					location.reload();
				}
			}
			
		});
	});

});
