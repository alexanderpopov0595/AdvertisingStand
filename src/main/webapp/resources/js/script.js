//web socket address
var webSocket = new WebSocket("ws://localhost:8080/AdvertisingStand/home");
//behavior on open event
webSocket.onopen = function (event) {
    //get gallery content from session
    var gallery = localStorage.getItem("gallery");
    //get gallery element and put gallery content in
    document.getElementsByClassName("gallery")[0].innerHTML = gallery;
}
//behavior on message event
webSocket.onmessage = function (event) {
    //get gallery div
    var gallery = document.getElementsByClassName("gallery")[0];
    //clear div container
    gallery.innerHTML = "";
    // var json='[{"id": 1,"name": "Cheap electric", "price": 100}, {"id": 2, "name": "Good electric", "price": 300}]';
    // i &lt;
    //parse json message
    var obj = JSON.parse(event.data);
    for (var i = 0; i < obj.length; i++) {
        //get single object
        var object = obj[i];
        //create table, tr and td
        var table = document.createElement("table");
        var tr1 = document.createElement("tr");
        var td1 = document.createElement("td");
        //create img with width 300px, src - link to product image by id or link to default product image
        var image = document.createElement("img");
        image.setAttribute("width", "300px");
        image.setAttribute("src", "http://localhost:8081/Onlinestore/images/products/" + object.id + ".jpg");
        image.setAttribute("onerror", "this.src='http://localhost:8081/Onlinestore/resources/img/product.jpg'");
        //put image to td, td to tr and tr to table
        td1.appendChild(image);
        tr1.appendChild(td1);
        table.appendChild(tr1);
        //create new tr,td and link to product page
        var tr2 = document.createElement("tr");
        var td2 = document.createElement("td");
        var a = document.createElement('a');
        //link text consists of product name and product price
        var linkText = document.createTextNode(object.name + " price:" + object.price);
        a.appendChild(linkText);
        //link to product page
        a.href = "http://localhost:8081/Onlinestore/products/" + object.id;
        //append a to td, td to tr, tr to table
        td2.appendChild(a);
        tr2.appendChild(td2);
        table.appendChild(tr2);
        //append all to gallery
        gallery.appendChild(table);
    }
    //save gallery content in session
    localStorage.setItem("gallery", gallery.innerHTML);
    // get gallery element
    gallery = document.getElementsByClassName("gallery")[0];
    //send gallery element to server
    webSocket.send(gallery.innerHTML);
}

