create table interface_info
(
    id             BIGINT(19) auto_increment comment '主键'
        primary key,
    name           VARCHAR(256)                           not null comment '名称',
    requestHeader  TEXT(65535)                            null comment '请求头',
    responseHeader TEXT(65535)                            null comment '响应头',
    userId         BIGINT(19)                             not null comment '创建人',
    method         VARCHAR(256)                           not null comment '请求类型',
    url            VARCHAR(512)                           not null comment '接口地址',
    status         INT(10)      default 0                 not null comment '接口状态 （0-关闭，1-开启）',
    description    VARCHAR(256)                           null comment '描述',
    createTime     DATETIME(19) default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime     DATETIME(19) default CURRENT_TIMESTAMP not null comment '更新时间',
    isDeleted      TINYINT(3)   default 0                 not null comment '是否删除(0-未删, 1-已删)',
    requestParams  TEXT(65535)                            null comment '请求参数'
)
    comment '接口信息';

create table post
(
    id         BIGINT(19) auto_increment comment 'id'
        primary key,
    title      VARCHAR(512)                           null comment '标题',
    content    TEXT(65535)                            null comment '内容',
    tags       VARCHAR(1024)                          null comment '标签列表（json 数组）',
    thumbNum   INT(10)      default 0                 not null comment '点赞数',
    favourNum  INT(10)      default 0                 not null comment '收藏数',
    userId     BIGINT(19)                             not null comment '创建用户 id',
    createTime DATETIME(19) default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime DATETIME(19) default CURRENT_TIMESTAMP not null comment '更新时间',
    isDelete   TINYINT(3)   default 0                 not null comment '是否删除'
)
    comment '帖子';

create index idx_userId
    on post (userId);

create table post_favour
(
    id         BIGINT(19) auto_increment comment 'id'
        primary key,
    postId     BIGINT(19)                             not null comment '帖子 id',
    userId     BIGINT(19)                             not null comment '创建用户 id',
    createTime DATETIME(19) default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime DATETIME(19) default CURRENT_TIMESTAMP not null comment '更新时间'
)
    comment '帖子收藏';

create index idx_postId
    on post_favour (postId);

create index idx_userId
    on post_favour (userId);

create table post_thumb
(
    id         BIGINT(19) auto_increment comment 'id'
        primary key,
    postId     BIGINT(19)                             not null comment '帖子 id',
    userId     BIGINT(19)                             not null comment '创建用户 id',
    createTime DATETIME(19) default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime DATETIME(19) default CURRENT_TIMESTAMP not null comment '更新时间'
)
    comment '帖子点赞';

create index idx_postId
    on post_thumb (postId);

create index idx_userId
    on post_thumb (userId);

create table user
(
    id           BIGINT(19) auto_increment comment 'id'
        primary key,
    userAccount  VARCHAR(256)                           not null comment '账号',
    userPassword VARCHAR(512)                           not null comment '密码',
    unionId      VARCHAR(256)                           null comment '微信开放平台id',
    mpOpenId     VARCHAR(256)                           null comment '公众号openId',
    userName     VARCHAR(256)                           null comment '用户昵称',
    userAvatar   VARCHAR(1024)                          null comment '用户头像',
    userProfile  VARCHAR(512)                           null comment '用户简介',
    userRole     VARCHAR(256) default 'user'            not null comment '用户角色：user/admin/ban',
    accessKey    VARCHAR(512)                           not null comment 'accessKey',
    secretKey    VARCHAR(512)                           not null comment 'secretKey',
    createTime   DATETIME(19) default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   DATETIME(19) default CURRENT_TIMESTAMP not null comment '更新时间',
    isDelete     TINYINT(3)   default 0                 not null comment '是否删除'
)
    comment '用户';

create index idx_unionId
    on user (unionId);

create table user_interface_info
(
    id              BIGINT(19) auto_increment comment '主键'
        primary key,
    userId          BIGINT(19)                             not null comment '调用用户 id',
    interfaceInfoId BIGINT(19)                             not null comment '接口 id',
    totalNum        INT(10)      default 0                 not null comment '总调用次数',
    leftNum         INT(10)      default 0                 not null comment '剩余调用次数',
    status          INT(10)      default 0                 not null comment '0-正常，1-禁用',
    createTime      DATETIME(19) default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime      DATETIME(19) default CURRENT_TIMESTAMP not null comment '更新时间',
    isDelete        TINYINT(3)   default 0                 not null comment '是否删除(0-未删, 1-已删)'
)
    comment '用户调用接口关系';

