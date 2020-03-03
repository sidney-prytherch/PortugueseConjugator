let process = (prefix, takeaway, str) => {
    let newStr = str.trim().replace(/[\s\t]+/g, ",");
    let strings = newStr.split(",");
    for (let i = 0; i < strings.length; i++) {
        if (strings[i].indexOf(takeaway) >= 0) {
            strings[i] = strings[i].replace(takeaway, prefix + "\"") + "\"";
        } else {
            console.log("ERROR - takeaway missing");
        }
    }

    var x = document.createElement("INPUT");
    x.setAttribute("type", "text");
    x.setAttribute("value", ("return new String[] { " + strings[0] + ", " + strings[1] + ", " + strings[2] + ", " + strings[3] + ", " + strings[4] + ", " + strings[5] + "};").replace(/"-"|\w+\s\+\s"-"/g, "null"));
    document.body.appendChild(x);
    x.select();
    x.setSelectionRange(0, 99999);
    document.execCommand("copy");
}
