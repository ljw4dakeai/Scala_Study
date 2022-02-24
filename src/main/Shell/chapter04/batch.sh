#!/bin/zsh
#cd /Users/zoujiahao/IDEA/Scala_Study/src/main/Shell/chapter04/
#
#touch batch.txt
#
#echo "nihaozoujiahao xue shell" >> batch.txt

##只有字符型，输出只有字符
#c=1+1
#echo $c
##含有空格的变量带上引号
#x="zoujiaho Love lijinwen"
#echo $x

#特殊变量$n
# shellcheck disable=SC1073
# bash batch.sh zoujiahao love me
#        $0      $1        $2  $3
echo $0 #脚本本身
echo $1 #参数1
echo $2 #参数2
echo $3 #参数3


#$#
echo $# #输入参数个数

#$*
echo $*
#echo $@
echo $#


#$? 最后一条命名的返会状态 正确执行0,不正确执行非0
echo $?


