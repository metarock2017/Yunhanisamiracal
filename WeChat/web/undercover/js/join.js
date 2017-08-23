window.onload = function () {
    init();
}

const init = () => {
    let p_one = document.querySelector(".p_one"),
        p_two = document.querySelector(".p_two"),
        p_three = document.querySelector(".p_three");
    p_one.style.setProperty("top", "0");
    p_one.style.setProperty("opacity", "1");
    p_two.style.setProperty("top", "0");
    p_two.style.setProperty("opacity", "1");
    p_three.style.setProperty("top", "0");
    p_three.style.setProperty("opacity", "1");

    let input_one = document.querySelector(".input_one"),
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

let join_button = document.querySelector(".join_button");

join_button.addEventListener('click', () => {
    let input_one = document.querySelector(".input_one"),
        input_two = document.querySelector(".input_two"),
        input_three = document.querySelector(".input_three");

    if (input_three.value !== "") {
        let ajax = new Ajax({
            method: 'post',
            url: 'http://ghan.s1.natapp.link/player/create',
            callback: (res) => {
                let jsonObj = JSON.parse(res);
                if (jsonObj.errorCode != undefined) {
                    input_one.value = jsonObj.playerWord;
                    input_two.value = jsonObj.playerNum;
                    input_three.value = jsonObj.roomId;
                } else {
                    input_three.value = jsonObj.errorMsg;
                }
                input_one.style.setProperty("color", "rgba(255,255,255,.7)");
                input_two.style.setProperty("color", "rgba(255,255,255,.7)");
                input_three.style.setProperty("color", "rgba(255,255,255,.7)");
            },
            data: {
                roomId: input_three.value
            }
        });
        ajax.send();
    } else {
        input_three.value = "输入房间号";
    }
});