<%@ page  pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>

<html>

  <head>
    <title>The Organizer</title>
    <link rel="stylesheet" href="css/styles.css" type="text/css">
    <script type="text/javascript" src="js/buttonsAndTabs.js"></script>
    <script>
      var tabsButtonsEnabled = true;
      var rolloverImages = new Array();
    </script>
  </head>

  <body class="cssMain" onLoad="createRolloverImages('createAccount');">
    <center>
      <br>
      <img src="img/title.gif">
      <br><br><br><br>
      <ww:if test="message!=null">
        <div class="cssError"><ww:property value="message" /></div>
      </ww:if>
      <ww:else>
        <div class="cssError">&nbsp;</div>
      </ww:else>
      <br><br><br>
      <ww:form action="accountCreate" cssClass="cssMain">
        <ww:textfield label="用户名" name="username"
          value="%{username}" cssClass="cssInput0" maxlength="20" size="21"
          onfocus="this.className='cssInput1';"
          onblur="this.className='cssInput0';" />
        <ww:textfield label="密码" name="password"
          value="%{password}" cssClass="cssInput0" maxlength="20" size="21"
          onfocus="this.className='cssInput1';"
          onblur="this.className='cssInput0';" />
        <ww:textfield label="重复密码" name="password_2"
          value="%{password_2}" cssClass="cssInput0" maxlength="20" size="21"
          onfocus="this.className='cssInput1';"
          onblur="this.className='cssInput0';" />
        <ww:submit type="image" src="img/createAccount0.gif" id="createAccount"
          onmouseover="rollover(this, 'createAccount');"
          onmouseout="rollout(this, 'createAccount');" />
      </ww:form>
    </center>
  </body>

</html>
