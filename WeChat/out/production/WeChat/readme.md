流程概览

- 房间信息
  - Redis 存 open_id,房间信息Json
  - set存可用房间号(每次请求取出一个)
  - open_id过期时压入set
    - 设置一个新的有序set
    - 按时间戳排序
    - 每次请求生成时间戳和Redis中时间戳比对
    - 如果过期把过期的open_id压入set
    - 否则进行其他操作
- 验证用户是否已经创建房间
  - 所有cookie设置过期时间与Redis相同
  - 设置cookie: is_h(is_host) = (0:房主;1:玩家) AESC加盐加解密
  - 设置cookie: o_i(open_id) = (多个open_id用分号隔开) AESC加盐加解密
  - 设置cookie: r_i(room_info) = (当前对应房间信息Json) AESC加盐加解密
    - 解析房间信息
      - 失败
        - 查询open_id是否存在于Redis中
          - 存在:跳至下一步
          - 不存在:清除cookie,返回错误信息Json
      - 成功:返回Json(房间信息)(或改变单词后的新房间Json)
  - 每次请求解析cookie 分离出用户类型和用户所处房间(只选择最后一个加入的房间信息)
    - 同时根据前端Json数据 对请求进行分类
      - 房主:返回房间信息
      - 用户:可加入房间,可创建房间
        - 创建房间后不可加入房间 设置cookie is_h=0
  - 在服务层对请求进行过滤

接口

- /room/create
  - 创建房间
  - 提交Json中确定创建房间的模式
    - is_u(is_unique):0/1
    - 快速创建
      - 创建房间JavaBean
      - 随机词语
    - 自定义房间
      - //TODO...............
    - 返回房间Json

- /room/patch
  - 更新房间信息
    - 提交Json改变单词
    - 刷新过期时间
    - 返回新房间Json
- /player/create
  - 添加玩家
    - 获取提交的open_id
    - 取房间Json字符串 解析为JavaBean
    - 玩家+1(伪随机函数确定身份)
    - 创建玩家JavaBean
    - 返回玩家Json
- 数据均为Json格式
- open_id作为关键字段

Redis结构

- 单词Hash word_id对应word JavaBean字符串
- 可用房间Set 存放可用房间号
- 房间Hash open_id对应room JavaBean字符串
- 用户认证Hash md5对应open_id
- 正在使用的房间有序Set 按照score 排序 score存放创建房间时的时间戳 只有open_id字段
