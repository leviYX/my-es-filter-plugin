# 1、简介
就是一个插件

# 2、打包编译
mvn clean install
然后把生成的zip插件安装到ES中,具体操作如下：把生成的zip包放到指定目录下比如D:\plugins。进入es的bin目录执行
elasticsearch-plugin.bat install file:///D:\plugins\levi-replace-processsor-1.0.0-SNAPSHOT.zip
然后重启es，日志中会加载该组件。
# 3、创建管道使用
# 建立索引
~~~json
  PUT test-index
  {
    "settings": {
      "number_of_replicas": 0
    }, 
    "mappings": {
      "properties": {
        "name":{
          "type": "keyword"
        }
      }
    }
  }
~~~


# 建立管道
~~~json
  PUT /_ingest/pipeline/my-filter_01
  {
    "processors": [
      {
        "levi_filter_word":{
          "myfield":"你要替换哪个字段",
          "myfilterWord":"你要替换的词"
        }
      }
    
    ]
  }
~~~


# 使用管道写入数据
~~~json
  POST test-index/_doc?pipeline=my-filter_01
  {
    "description":"nihao tmd"
  }
~~~


# 查看
~~~json
  GET my-index-01/_search
~~~




