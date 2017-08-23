window.onload = function() {
    init();
}

const init = () => {
    let p_one = document.querySelector(".p_one"),
        p_two = document.querySelector(".p_two"),
        p_three = document.querySelector(".p_three"),
        p_four = document.querySelector(".p_four");

    let input_one = document.querySelector(".input_one"),
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

let create_unique = document.querySelector('.create_unique');
let create_quick = document.querySelector(".create_quick");

create_quick.addEventListener('click', () => {
    let input_one = document.querySelector(".input_one"),
        input_two = document.querySelector(".input_two"),
        input_three = document.querySelector(".input_three"),
        input_four = document.querySelector(".input_four");

    let ajax = new Ajax({
        method: 'post',
        url: 'http://ghan.s1.natapp.link/room/create',
        callback: (res) => {
            let jsonObj = JSON.parse(res);
            input_one.value = jsonObj.wordOne;
            input_two.value = jsonObj.wordTwo;
            input_three.value = jsonObj.maxNum;
            input_four.value = jsonObj.openId;

            input_one.style.setProperty("color", "rgba(255,255,255,.7)");
            input_two.style.setProperty("color", "rgba(255,255,255,.7)");
            input_three.style.setProperty("color", "rgba(255,255,255,.7)");
            input_four.style.setProperty("color", "rgba(255,255,255,.7)");
        },
        data: {
            maxNum: '10'
        }
    });
    ajax.send();
});

create_unique.addEventListener('click', () => {
    let input_one = document.querySelector(".input_one"),
        input_two = document.querySelector(".input_two"),
        input_three = document.querySelector(".input_three"),
        input_four = document.querySelector(".input_four");

    let ajax = new Ajax({
        method: 'post',
        url: 'http://ghan.s1.natapp.link/room/create/unique',
        callback: (res) => {
            let jsonObj = JSON.parse(res);
            input_one.value = jsonObj.wordOne;
            input_two.value = jsonObj.wordTwo;
            input_three.value = jsonObj.maxNum;
            input_four.value = jsonObj.openId;

            input_one.style.setProperty("color", "rgba(255,255,255,.7)");
            input_two.style.setProperty("color", "rgba(255,255,255,.7)");
            input_three.style.setProperty("color", "rgba(255,255,255,.7)");
            input_four.style.setProperty("color", "rgba(255,255,255,.7)");
        },
        data: {
            word_one: input_one.value,
            word_two: input_two.value,
            maxNum: input_three.value
        }
    });
    ajax.send();
})