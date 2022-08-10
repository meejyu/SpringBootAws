var main = {
    init : function () {
        alert("gdd");
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        alert("gdd");
    },
    save : function () {
        alert("gdd");
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };
        alert("gdd");
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
main.init();
alert("gdgd");
/*
    브라우저의 스코프는 공용 공간으로 쓰이기 때문에 나중에 로딩된 js의 먼저 로딩된 js의 function을 덮어쓰게 됩니다.
    이런 문제를 피하기 위해서 index.js만의 유효범위를 만들어 사용한다.
    var index 이란 객체안에서만 function이 유효하기 때문에 JS와 겹칠 위험이 사라진다.
 */