<%@ page  pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>

<img src="img/head_dayAtAGlance.gif">
<br><br>

<div class="cssScrollContent">

  <div class="cssDAAGHeading">今天到期行程:</div>
  <ww:if test="%{!tasks.isEmpty()}">
    <ww:iterator value="tasks">
      <form>
        <input type="hidden" name="createdDT" value="<ww:property
          value="createdDT"/>">
        <table border="0" cellpadding="0" cellspacing="0" class="cssDAAGItem">
          <tr>
            <td width="1">
              <input type="image" src="img/edit0.gif" id="edit"
                align="absmiddle" onmouseover="rollover(this);"
                onmouseout="rollout(this);"
                onclick="taskRetrieve(this.form);return false;">
            </td>
            <td width="10">&nbsp;</td>
            <td>
             主题: <ww:property value="subject" />
            </td>
          </tr>
        </table>
      </form>
    </ww:iterator>
  </ww:if>
  <ww:else>
    今天尚未安排行程
  </ww:else>

  <br><br><br>

  <div class="cssDAAGHeading">今天的计划:</div>
  <ww:if test="%{!appointments.isEmpty()}">
    <ww:iterator value="appointments">
      <form>
        <input type="hidden" name="createdDT" value="<ww:property
          value="createdDT"/>">
        <table border="0" cellpadding="0" cellspacing="0" class="cssMain">
          <tr>
            <td width="1">
              <input type="image" src="img/edit0.gif" id="edit"
                align="absmiddle" onmouseover="rollover(this);"
                onmouseout="rollout(this);"
                onclick="appointmentRetrieve(this.form);return false;">
            </td>
            <td width="10">&nbsp;</td>
            <td>
              主题: <ww:property value="subject" />
              <br>
              时间:
              <ww:property value="startTime" />
              -
              <ww:property value="endTime" />
              <br>
              位置: <ww:property value="location" />
            </td>
          </tr>
        </table>
      </form>
    </ww:iterator>
  </ww:if>
  <ww:else>
  今天 没有约会
  </ww:else>

</div>
