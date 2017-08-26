window.onload = function () {
    init();
}

const init = () => {
    var p_one = document.querySelector(".p_one"),
        p_two = document.querySelector(".p_two"),
        p_three = document.querySelector(".p_three"),
        p_four = document.querySelector(".p_four");

    var input_one = document.querySelector(".input_one"),
        input_two = document.querySelector(".input_two"),
        input_three = document.querySelector(".input_three"),
        input_four = document.querySelector(".input_four");

    p_one.style.setProperty("top", "0");
    p_one.style.setProperty("opacity", "1");
    p_two.style.setProperty("top", "0");
    p_two.style.setProperty("opacity", "1");
    p_three.style.setProperty("top", "0");
    p_three.style.setProperty("opacity", "1");
    p_four.style.setProperty("top", "0");
    p_four.style.setProperty("opacity", "1");

    input_one.style.setProperty("top", "0");
    input_one.style.setProperty("opacity", "1");
    input_two.style.setProperty("top", "0");
    input_two.style.setProperty("opacity", "1");
    input_three.style.setProperty("top", "0");
    input_three.style.setProperty("opacity", "1");
    input_four.style.setProperty("top", "0");
    input_four.style.setProperty("opacity", "1");

    document.querySelector(".create_unique").style.setProperty("top", "0");
    document.querySelector(".create_unique").style.setProperty("opacity", "1");
    document.querySelector(".create_quick").style.setProperty("top", "0");
    document.querySelector(".create_quick").style.setProperty("opacity", "1");

}
var create_unique = document.querySelector('.create_unique'),
    create_quick = document.querySelector(".create_quick"),
    create_change = document.querySelector(".create_change"),
    create_clear = document.querySelector(".create_clear");

create_quick.onclick = function() {
    alert("click");
    var input_one = document.querySelector(".input_one"),
        input_two = document.querySelector(".input_two"),
        input_three = document.querySelector(".input_three"),
        input_four = document.querySelector(".input_four");

    var ajax = new Ajax({
        method: 'post',
        url: 'http://ghan.s1.natapp.link/room/create',
        callback: (res) => {
            var jsonObj = JSON.parse(res);
            input_one.value = jsonObj.wordOne;
            input_two.value = jsonObj.wordTwo;
            input_three.value = jsonObj.maxNum;
            input_four.value = jsonObj.openId;

            create_change.style.setProperty("z-index", "1");
            create_change.style.setProperty("opacity", "1");
            create_change.style.setProperty("top", "-65px");
            create_clear.style.setProperty("z-index", "1");
            create_clear.style.setProperty("opacity", "1");
            create_clear.style.setProperty("top", "-195px");
        },
        data: {
            maxNum: '10'
        }
    });
    ajax.send();
};


create_unique.onlick = function() {
    var input_one = document.querySelector(".input_one"),
        input_two = document.querySelector(".input_two"),
        input_three = document.querySelector(".input_three"),
        input_four = document.querySelector(".input_four");

    var ajax = new Ajax({
        method: 'post',
        url: 'http://ghan.s1.natapp.link/room/create',
        callback: (res) => {
            var jsonObj = JSON.parse(res);
            input_one.value = jsonObj.wordOne;
            input_two.value = jsonObj.wordTwo;
            input_three.value = jsonObj.maxNum;
            input_four.value = jsonObj.openId;

            create_change.style.setProperty("z-index", "1");
            create_change.style.setProperty("opacity", "1");
            create_change.style.setProperty("top", "-65px");
            create_clear.style.setProperty("z-index", "1");
            create_clear.style.setProperty("opacity", "1");
            create_clear.style.setProperty("top", "-195px");
        },
        data: {
            maxNum: Math.floor(parseInt(input_three.value)).toString()
        }
    });
    var maxNum = parseInt(input_three.value);
    if (!isNaN(maxNum)) {
        if (maxNum < 4) {
            input_three.value = "最少四个人嘛";
        } else if (maxNum > 15) {
            input_three.value = "最多15人嘛";
        } else {
            ajax.send();
        }
    } else {
        input_three.value = "输入一个正常的数字呀";
    }
}

create_change.onclick = function() {
    var input_one = document.querySelector(".input_one"),
        input_two = document.querySelector(".input_two"),
        input_three = document.querySelector(".input_three"),
        input_four = document.querySelector(".input_four");

    var ajax = new Ajax({
        method: 'post',
        url: 'http://ghan.s1.natapp.link/room/patch',
        callback: (res) => {
            if (res != null) {
                var jsonObj = JSON.parse(res);
                if (jsonObj.errorCode == 500) {
                    alert(jsonObj.errorMsg);
                    location.reload(true);
                } else {
                    input_one.value = jsonObj.wordOne;
                    input_two.value = jsonObj.wordTwo;
                    input_three.value = jsonObj.maxNum;
                    input_four.value = jsonObj.openId;
                }
            } else {
                location.reload(true);
            }
        },
        data: {
            maxNum: Math.floor(parseInt(input_three.value)).toString()
        }
    });
    var maxNum = parseInt(input_three.value);
    if (!isNaN(maxNum)) {
        if (maxNum < 4) {
            input_three.value = "最少四个人嘛";
        } else if (maxNum > 15) {
            input_three.value = "最多15人嘛";
        } else {
            ajax.send();
        }
    } else {
        input_three.value = "输入一个正常的数字呀";
    }
};

create_clear.onclick = function(){
    var input_one = document.querySelector(".input_one"),
        input_two = document.querySelector(".input_two"),
        input_three = document.querySelector(".input_three"),
        input_four = document.querySelector(".input_four");

    var ajax = new Ajax({
        method: 'post',
        url: 'http://ghan.s1.natapp.link/room/clear',
        callback: (res) => {
            if (res != null) {
                var jsonObj = JSON.parse(res);
                if (jsonObj.errorCode == 500) {
                    alert(jsonObj.errorMsg);
                    location.reload(true);
                } else {
                    input_one.value = jsonObj.wordOne;
                    input_two.value = jsonObj.wordTwo;
                    input_three.value = jsonObj.maxNum;
                    input_four.value = jsonObj.openId;
                }
            } else {
                location.reload(true);
            }
        },
        data: {}
    });
    ajax.send();
};