#!/bin/zsh

#while [ 条件判断 ]
#do
#  程序
#done


#从1加到100
s=0
i=1
while [ $i -le 100 ]
do
  s=$[$s + $i]
  i=$[$i + 1]
done
echo $s