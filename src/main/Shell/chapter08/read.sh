#!/bin/zsh

#read(选项)(参数) bash中使用

#选项 -p 指定读取时的提示符
#    -t 指定读取时的等待时间
#参数
#    变量 指定读取值的变量名
read -t 20 -p '请输入你的名字(20s内)' NAME
echo $NAME