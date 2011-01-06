<%@ page  pageEncoding="UTF-8"%>
<%
  String requestURI = (String)request.getAttribute("webwork.request_uri");
  requestURI = requestURI.toLowerCase();
  String headerFile = ""; String whatItem   = "";
  String whatOp     = ""; String targetFunc = "";
  if (requestURI.indexOf("account") != -1) {
    headerFile = "myAccount"; whatItem = "你的账户";
    if (requestURI.indexOf("update") != -1) {
      targetFunc = "showDayAtAGlance();";
    } else {
      targetFunc = "window.location='index.jsp';";
    }
  }
  if (requestURI.indexOf("appointment") != -1) {
    headerFile = "appointments"; whatItem = "约会";
    targetFunc = "showAppointments();";
  }
  if (requestURI.indexOf("note") != -1) {
    headerFile = "notes"; whatItem = "日记";
    targetFunc = "showNotes();";
  }
  if (requestURI.indexOf("task") != -1) {
    headerFile = "tasks"; whatItem = "任务";
    targetFunc = "showTasks();";
  }
  if (requestURI.indexOf("contact") != -1) {
    headerFile = "contacts"; whatItem = "联系人";
    targetFunc = "showContacts();";
  }
  if (requestURI.indexOf("create") != -1) { whatOp = "创建"; }
  if (requestURI.indexOf("update") != -1) { whatOp = "更新"; }
  if (requestURI.indexOf("delete") != -1) { whatOp = "删除"; }
%>
<img src="img/head_<%=headerFile%>.gif">
<br><br>

<%=whatItem%> 已经 <%=whatOp%>.
<br><br>

<input type="image" src="img/ok0.gif" id="ok"
  onmouseover="rollover(this, 'ok');" onmouseout="rollout(this, 'ok');"
  onclick="<%=targetFunc%>">
