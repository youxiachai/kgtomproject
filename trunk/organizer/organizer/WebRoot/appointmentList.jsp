<%@ page  pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>

<ww:if test="view.equalsIgnoreCase('day')">
  <img src="img/head_dayView.gif">
</ww:if>
<ww:if test="view.equalsIgnoreCase('week')">
  <img src="img/head_weekView.gif">
</ww:if>
<ww:if test="view.equalsIgnoreCase('month')">
  <img src="img/head_monthView.gif">
</ww:if>
<ww:if test="view.equalsIgnoreCase('year')">
  <img src="img/head_yearView.gif">
</ww:if>
  <br><br>

<div class="cssScrollContent">

  <ww:if test="%{!appointments.isEmpty()}">
    <ww:iterator value="appointments">
      <form>
        <input type="hidden" name="createdDT" value="<ww:property
          value="createdDT"/>">
        <table border="0" cellpadding="0" cellspacing="0" class="cssMain">
          <tr>
            <td>
              <input type="image" src="img/edit0.gif" id="edit"
                align="absmiddle" onmouseover="rollover(this);"
                onmouseout="rollout(this);"
                onclick="appointmentRetrieve(this.form);return false;">
            </td>
            <td width="10">&nbsp;</td>
            <td>
              日期: <ww:property value="appointmentDate" />
              <br>
              主题: <ww:property value="subject" />
              <br>
              时间:
              <ww:property value="startTime" />
              -
              <ww:property value="endTime" />
              <br>
              地点: <ww:property value="location" />
            </td>
          </tr>
        </table>
      </form>
    </ww:iterator>
  </ww:if>
  <ww:else>
    今天没有约会
  </ww:else>

</div>
