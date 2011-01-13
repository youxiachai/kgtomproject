var taskMenu1;
var taskMenu2;
var taskMenu3;
var taskMenu4;
var taskMenu5;
var taskMenu6;
var taskMenu7;



var item1;
var item2;
var item3;
var item4;
var item5;
var item6;
var item7;
var item8;
var item9;
var item10;
var item11;
var item12;
var item13;
var item14;
var item15;

TaskMenu.setStyle("../../../Public/style/admin_left.css"); 

window.onload = function()
{
	TaskMenu.setHeadMenuSpecial(true);
	item1 = new TaskMenuItem("我的资料","../../../Public/image/admin/left_demo.gif","parent.window.frames[2].location.href='TaskMenu_Demo.html'");
	item2 = new TaskMenuItem("添加管理员","../../../Public/image/admin/left_demo.gif","parent.window.frames[2].location.href='TaskMenu_API.html'");
	item3 = new TaskMenuItem("公司信息","../../../Public/image/admin/left_demo.gif","parent.window.frames[2].location.href='../Message/summary'");
	
	item4 = new TaskMenuItem("公司联系方式","../../../Public/image/admin/left_demo.gif","parent.window.frames[2].location.href='../Message/contacts'");
	

	item8 = new TaskMenuItem("已审核留言","../../../Public/image/admin/left_demo.gif","parent.window.frames[2].location.href='../Message/managerAuditMessage'");
	item9 = new TaskMenuItem("未审核留言","../../../Public/image/admin/left_demo.gif","parent.window.frames[2].location.href='../Message/managerUnauditMessage'");
	
	
	
	item12 = new TaskMenuItem("文件管理","../../../Public/image/admin/left_demo.gif","parent.window.frames[2].location.href='../upload/managerFile'");
	item13 = new TaskMenuItem("图片上传","../../../Public/image/admin/left_demo.gif","parent.window.frames[2].location.href='../upload/addFile'");
	item14 = new TaskMenuItem("<B>版权</B>");
	item15 = new TaskMenuItem();
	item15.setLabel("珠海创星公司 <b style='color:purple'>1.0</b>");

	taskMenu1 = new TaskMenu("控制面板");
	taskMenu1.add(item1);
	taskMenu1.add(item2);
	taskMenu1.init();
	
	taskMenu2 = new TaskMenu("公司信息");
	taskMenu2.add(item3);
	taskMenu2.init();
	taskMenu3 = new TaskMenu("公司联系方式");
	taskMenu3.add(item4);
	taskMenu3.init();

	
	taskMenu4 = new TaskMenu("留言");
	taskMenu4.add(item8);
	taskMenu4.add(item9);
	taskMenu4.init();


	
	taskMenu6 = new TaskMenu("上传文件");
	taskMenu6.add(item12);
	taskMenu6.add(item13);
	taskMenu6.init();
	
	taskMenu7 = new TaskMenu("版权信息");
	taskMenu7.setBackground("../../../Public/image/admin/left_bg.gif");
	taskMenu7.add(item14);
	taskMenu7.add(item15);
	taskMenu7.init();
	
}