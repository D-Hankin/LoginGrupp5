let productName = Array.from(document.getElementsByClassName("productName"));
let productNameText = [];
let productImageClass = Array.from(document.getElementsByClassName("productImage"));

for (let i = 0; i < productName.length; i++) {
    productNameText.push(productName[i].innerText);
    productImageClass[i].src = ("/images/" + productName[i].innerText.toLowerCase() + ".jpg");
}