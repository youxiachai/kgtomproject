<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="ww" uri="webwork" %>

<img src="img/head_tasks.gif">
<br><br>

<div class="cssDAAGHeading">
<%
  String requestURI = (String)request.getAttribute("webwork.request_uri");
  requestURI = requestURI.toLowerCase();
  if (requestURI.indexOf("create") != -1) {
    out.println("创建任务:");
  } else {
    out.println("编辑任务:");
  }
%>
</div>
<br>

<div class="cssScrollContent">

  <ww:form cssClass="cssMain" onsubmit="return false">
    <ww:hidden name="createdDT" value="%{task.createdDT}" />
    <ww:hidden name="due" value="%{task.due}" />
    <ww:textfield label="主题" name="subject" value="%{task.subject}"
      cssClass="cssInput0" maxlength="100" size="48"
      onfocus="this.className='cssInput1';"
      onblur="this.className='cssInput0';" />
    <ww:select label="状态" name="status"
      list="#{'I':'草稿', 'C':'完成'}" cssClass="cssInput0"
      onfocus="this.className='cssInput1';"
      onblur="this.className='cssInput0';" />
    <ww:select label="优先级" name="priority"
      list="#{'N':'正常', 'L':'低', 'H':'高'}" cssClass="cssInput0"
      onfocus="this.className='cssInput1';"
      onblur="this.className='cssInput0';" />
    <tr>
      <td><label>日期</label></td>
      <td>
        <ww:select name="dueMonth" theme="simple"
          list="#{'':'', '01':'一月', '02':'二月', '03':'三月',
          '04':'四月', '05':'五月', '06':'六月', '07':'七月', '08':'八月',
          '09':'九月', '10':'十月', '11':'十一月',
          '12':'十二月'}"
          cssClass="cssInput0"
          onfocus="this.className='cssInput1';"
          onblur="this.className='cssInput0';" />
        &nbsp;
        <ww:select name="dueDay" theme="simple"
          list="#{'':'', '01':'01', '02':'02', '03':'03', '04':'04', '05':'05',
          '06':'06', '07':'07', '08':'08', '09':'09', '10':'10', '11':'11',
          '12':'12', '13':'13', '14':'14', '15':'15', '16':'16', '17':'17',
          '18':'18', '19':'19', '20':'20', '21':'21', '22':'22', '23':'23',
          '24':'24', '25':'25', '26':'26', '27':'27', '28':'28', '29':'29',
          '30':'30', '31':'31'}"
          cssClass="cssInput0"
          onfocus="this.className='cssInput1';"
          onblur="this.className='cssInput0';" />
        &nbsp;
        <ww:select name="dueYear" theme="simple"
          list="#{'':'', '2006':'2006', '2007':'2007', '2008':'2008',
          '2009':'2009', '2010':'2010', '2011':'2011', '2012':'2012',
          '2013':'2013', '2014':'2014', '2015':'2015', '2016':'2016',
          '2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020'}"
          cssClass="cssInput0"
          onfocus="this.className='cssInput1';"
          onblur="this.className='cssInput0';" />
      </td>
    </tr>
    <ww:textarea label="内容" cols="45" rows="15" name="comments"
      value="%{task.comments}" cssClass="cssInput0"
      onfocus="this.className='cssInput1';"
      onblur="this.className='cssInput0';" />
    <% if (((String)request.getAttribute(
         "webwork.request_uri")).indexOf("taskCreate") != -1) { // Create %>
      <ww:submit type="image" src="img/save0.gif" id="save"
        onmouseover="rollover(this, 'save');"
        onmouseout="rollout(this, 'save');"
        onclick="taskCreate(this.form);return false;" />
    <% } else { // Edit %>
      <ww:submit type="image" src="img/save0.gif" id="save"
        onmouseover="rollover(this, 'save');"
        onmouseout="rollout(this, 'save');"
        onclick="taskUpdate(this.form);return false;" />
      <ww:submit type="image" src="img/delete0.gif" id="delete"
        onmouseover="rollover(this, 'delete');"
        onmouseout="rollout(this, 'delete');"
        onclick="taskDelete(this.form);return false;" />
    <% } %>
  </ww:form>

</div>
