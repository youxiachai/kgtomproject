<?php 
/**
 * 入口文件
 * @author TOM
 * @version 1.0
 * create Date 12 15,2010
 */

//  定义 ThinkPHP 框架路径 
define( 'THINK_PATH' ,  './ThinkPHP/' ); 

// 定义项目名称和路径 
define( 'APP_NAME' ,  'App' ); 
define( 'APP_PATH' ,  'App' ); 

//
//define ( 'BUILD_DIR_SECURE' , true ); 
//define ( 'DIR_SECURE_FILENAME' ,  'default.html' ); 
//define ( 'DIR_SECURE_CONTENT' ,  '非法访问!' ); 

//   加载框架入口文件
require(THINK_PATH."/ThinkPHP.php"); 

// 实例化一个网站应用实例 
App::run(); 

?> 
