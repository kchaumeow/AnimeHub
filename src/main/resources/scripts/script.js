const truncate = (str, maxlength) => {
    if (str.length > maxlength) {
        return str.slice(0, maxlength - 1) + '…';
    } else {
        return str;
    }
}

const goTruncate = () => {
    let elem1 = document.getElementById('str1');
    let elem2 = document.getElementById('str2');
    let elem3 = document.getElementById('str3');

    elem1.innerHTML = truncate(elem1.innerHTML, 40);
    elem2.innerHTML = truncate(elem2.innerHTML, 40);
    elem3.innerHTML = truncate(elem3.innerHTML, 40);
}

goTruncate();