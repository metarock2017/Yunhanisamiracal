window.onload = function () {
    init();
}

const init = () => {
    var p_one = document.querySelector(".p_one"),
        p_two = document.querySelector(".p_two"),
        p_three = document.querySelector(".p_three");
    p_one.style.setProperty("top", "0");
    p_one.style.setProperty("opacity", "1");
    p_two.style.setProperty("top", "0");
    p_two.style.setProperty("opacity", "1");
    p_three.style.setProperty("top", "0");
    p_three.style.setProperty("opacity", "1");

    var input_one = document.querySelector(".input_one"),
        input_two = document.querySelector(".input_two"),
        input_three = document.querySelector(".input_three");

    input_one.style.setProperty("top", "0");
    input_one.style.setProperty("opacity", "1");
    input_two.style.setProperty("top", "0");
    input_two.style.setProperty("opacity", "1");
    input_three.style.setProperty("top", "0");
    input_three.style.setProperty("opacity", "1");

    document.querySelector(".join_button").style.setProperty("top", "0");
    document.querySelector(".join_button").style.setProperty("opacity", "1");

}

var join_button = document.querySelector(".join_button");

join_button.addEventListener('click', () => {
    var input_one = document.querySelector(".input_one"),
        input_two = document.querySelector(".input_two"),
        input_three = document.querySelector(".input_three");

    var ajax = new Ajax({
        method: 'post',
        url: 'http://ghan.s1.natapp.link/player/create',
        callback: (res) => {
            var jsonObj = JSON.parse(res);
            if (jsonObj.errorCode == undefined) {
                input_one.value = jsonObj.playerWord;
                input_two.value = jsonObj.playerNum;
                input_three.value = jsonObj.roomId;
                document.querySelector(".pm").value = jsonObj.roomId;
            } else {
                input_three.value = jsonObj.errorMsg;
            }
        },
        data: {
            roomId: Math.floor(parseInt(input_three.value)).toString()
        }
    });
    var roomId = parseInt(input_three.value);
    if (!isNaN(roomId)) {
        if (roomId < 1000 || roomId > 9999) {
            input_three.value = "你这房间号是在逗我吗"
        } else {
            ajax.send();
        }
    } else {
        input_three.value = "输入一个正常的数字呀";
    }
});