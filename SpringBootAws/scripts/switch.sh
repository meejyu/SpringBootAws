#!/usr/bin/env bash

ABSPATH=$(readlink -f $0) # 현재 stop.sh가 속해 있는 경로를 찾는다, profile.sh의 경로를 찾기 위해 사용된다
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh # import 구문이라고 생각하면 됨, function을 사용할 수 있게 된다.

function switch_proxy() {

    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service_url.inc

    echo "> 엔진엑스 Reload"
    sudo service nginx reload



}

# 하나의 문장을 만들어 파이프라인(|)으로 넘겨주기 위해 echo를 사용한다, 엔진엑스가 변경할 프록시 주소를 생성한다, 쌍따옴표를 사용해야한다. 그렇지 않으면 인식하지 못함

# /etc/nginx/conf.d/service_url.inc 앞에서 넘겨준 문장을 service-url.inc에 덮어쓴다

# 엔진엑스 설정을 다시 불러온다, restart와는 다르게 끊김 없이 다시 불러온다. 중요한 설정들은 반영되지 ㅇ낳으므로 리스타트를 사용해야할 때도 있다, 지금은 reload로 가능한 부분이다.
