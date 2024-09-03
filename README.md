# 1、简介
就是一个插件

# 2、使用就是mvn clean install
然后把生成的zip插件安装到ES中

# 3、创建管道使用
# 建立索引
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

# 建立管道
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

# 使用管道写入数据
POST test-index/_doc?pipeline=my-filter_01
{
  "description":"nihao tmd"
}

# 查看
GET my-index-01/_search



