
$(function(){
	$("#info","#info2").button();
	
	$("#info").click(function(){
		$.ajax({
			   type: "POST",
			   url: "getMessage",
			   data: 'id='+$(this).parent().prev().prev().prev().prev().html(),
			   dataType:"json",
			   success: function(msg){
			     $('#infoId').html(msg.data.meg_id);
			     $('#infoName').html(msg.data.meg_username);
			     $('#infoEmail').html(msg.data.meg_email);
			     $('#infoQQ').html(msg.data.meg_qq);
			     $('#infoPhone').html(msg.data.meg_phone);
			     $('#infoContent').html(msg.data.meg_content);
			     $('#infoTime').html(msg.data.meg_createtime);
			     $('#infoTitle').html(msg.data.meg_title);
			   }
			});
		$("#showdiv").dialog({
			modal: true,
			title: "已审核留言详细信息",
			buttons: {
				关闭: function() {
					$( this ).dialog( "close" );
					location.reload();
				}
			}
			
		});
	});
	
	$("#info2").click(function(){
		$.ajax({
			   type: "POST",
			   url: "getMessage",
			   data: 'id='+$(this).parent().prev().prev().prev().prev().html(),
			   dataType:"json",
			   success: function(msg){
			     $('#infoId').html(msg.data.meg_id);
			     $('#infoName').html(msg.data.meg_username);
			     $('#infoEmail').html(msg.data.meg_email);
			     $('#infoQQ').html(msg.data.meg_qq);
			     $('#infoPhone').html(msg.data.meg_phone);
			     $('#infoContent').html(msg.data.meg_content);
			     $('#infoTime').html(msg.data.meg_createtime);
			     $('#infoTitle').html(msg.data.meg_title);
			   }
			});
		$("#showdiv2").dialog({
			modal: true,
			title: "未审核留言详细信息",
			buttons: {
				通过: function() {
					$("#reply").dialog({
						modal: true,
						title: "回复该留言",
						buttons: {
							回复: function(){
								
							},
							返回: function(){
								$(this).dialog("close");
							}
						}
					});
				},
				关闭: function() {
					$( this ).dialog( "close" );
					location.reload();
				}
			}
			
		});
	});
	
});
