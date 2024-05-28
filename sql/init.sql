-- 接口信息
create table if not exists yuapi.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '名称',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `userId` bigint not null comment '创建人',
    `method` varchar(256) not null comment '请求类型',
    `url` varchar(512) not null comment '接口地址',
    `status` int default 0 not null comment '接口状态 （0-关闭，1-开启）',
    `description` varchar(256) null comment '描述',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

INSERT INTO yuapi.`interface_info` (name, requestHeader, responseHeader, userId, method, url, status, description, createTime, updateTime, isDeleted)
VALUES
    ('获取用户信息', 'Authorization: Bearer token', 'Content-Type: application/json', 1, 'GET', 'https://api.example.com/user/info', 1, '获取用户信息的接口', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    ('创建订单', 'Authorization: Bearer token', 'Content-Type: application/json', 2, 'POST', 'https://api.example.com/order/create', 1, '创建订单的接口', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    ('更新用户信息', 'Authorization: Bearer token', 'Content-Type: application/json', 1, 'PUT', 'https://api.example.com/user/update', 1, '更新用户信息的接口', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    ('删除订单', 'Authorization: Bearer token', 'Content-Type: application/json', 3, 'DELETE', 'https://api.example.com/order/delete', 0, '删除订单的接口', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    ('获取产品列表', 'Authorization: Bearer token', 'Content-Type: application/json', 2, 'GET', 'https://api.example.com/products/list', 1, '获取产品列表的接口', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO yuapi.`interface_info` (name, requestHeader, responseHeader, userId, method, url, status, description, createTime, updateTime, isDeleted)
VALUES
    ('获取产品详情', 'Authorization: Bearer token', 'Content-Type: application/json', 1, 'GET', 'https://api.example.com/product/details', 1, '获取产品详细信息的接口', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    ('创建用户', 'Authorization: Bearer token', 'Content-Type: application/json', 4, 'POST', 'https://api.example.com/user/create', 1, '创建用户的接口', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    ('更新订单状态', 'Authorization: Bearer token', 'Content-Type: application/json', 3, 'PATCH', 'https://api.example.com/order/status', 1, '更新订单状态的接口', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    ('删除用户', 'Authorization: Bearer token', 'Content-Type: application/json', 4, 'DELETE', 'https://api.example.com/user/delete', 0, '删除用户的接口', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    ('获取订单列表', 'Authorization: Bearer token', 'Content-Type: application/json', 2, 'GET', 'https://api.example.com/orders/list', 1, '获取订单列表的接口', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);



-- 用户调用接口关系表
create table if not exists yuapi.`user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户 id',
    `interfaceInfoId` bigint not null comment '接口 id',
    `totalNum` int default 0 not null comment '总调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '0-正常，1-禁用',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系';