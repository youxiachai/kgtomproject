<?php
/**
 * 配置文件
 * @author 吴奇成
 * @version 1.0
 * create Date 12 15,2010
 */
return array(
	//'配置项'=>'配置值'
	'APP_DEBUG' => false,
 	'APP_GROUP_LIST' => 'Admin,Home', 
	'DEFAULT_GROUP' => 'Home',
	//URL 配置
//	'URL_HTML_SUFFIX' => '.shtml',		//URL 伪静态
	'HTML_CACHE_ON' => false, 
	
	//开启令牌验证
//	'TOKEN_ON' => true,  // 是否开启令牌验证    
//	'TOKEN_NAME' => '__hash__',    // 令牌验证的表单隐藏字段名称    
//	'TOKEN_TYPE' => 'md5',  //令牌哈希验证规则 默认为MD5   


	/* 数据库设置 */
    'DB_TYPE' => 'mysql',     // 数据库类型
	'DB_HOST' => 'localhost', // 服务器地址
	'DB_NAME' => 'zhhcx',          // 数据库名
	'DB_USER' => 'root',      // 用户名
	'DB_PWD' => '123',          // 密码
	'DB_PORT' => 3306,        // 端口
	'DB_PREFIX' => 'zhh_',    // 数据库表前缀
	'DB_SUFFIX' => '',          // 数据库表后缀
    'DB_FIELDTYPE_CHECK' => true,       // 是否进行字段类型检查
    'DB_FIELDS_CACHE' => true,        // 启用字段缓存
    'DEFAULT_CHARSET' => 'utf8', // 默认输出编码
    'DB_CHARSET' => 'utf8',      // 数据库编码默认采用utf8
    'DB_DEPLOY_TYPE' => 0, // 数据库部署方式:0 集中式(单一服务器),1 分布式(主从服务器)
    'DB_RW_SEPARATE' => false       // 数据库读写是否分离 主从式有效
);
?>