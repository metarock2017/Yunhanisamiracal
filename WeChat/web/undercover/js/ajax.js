function Ajax(obj) {
    this.method = obj.method || '';
    this.url = obj.url || '';
    this.callback = obj.callback || '';
    this.data = obj.data || '';
};

Ajax.prototype.send = function (method, url, callback, data) {
    var method = method || this.method;
    var data = data || this.data;
    var url = url || this.url;
    var callback = callback || this.callback;
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                callback(xhr.responseText);
            }
            else {
                console.log('check log');
            }
        }
    }
    if (method === 'get') {
        if (typeof data === 'object') {
            let data_send = '?';
            for (let key in data) {
                data_send += key + '=' + data[key];
                data_send += '&';
            }
            data_send = data_send.slice(0, -1);
            console.log(data_send);
        }
        xhr.open(method, url + data_send, true);
        xhr.send(null);
    } else if (method === 'post') {
        xhr.open(method, url, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(JSON.stringify(data));
    } else {
        console.log('undefined method:' + method);
        return false;
    }
}