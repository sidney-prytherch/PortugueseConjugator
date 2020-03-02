process = (prefix, takeaway, str) => {
    let newStr = str.replace(/[\s\t]+/g, ",");
    let strings = newStr.split(",");
    for (let i = 0; i < strings.length; i++) {
        if (strings[i].indexOf(takeaway) >= 0) {
            strings[i] = strings[i].replace(takeaway, prefix + "\"") + "\"";
        } else {
            console.log("ERROR - takeaway missing");
        }
    }
    console.log("return new String[] { " + strings[0] + ", " + strings[1] + ", " + strings[2] + ", " + strings[3] + ", " + strings[4] + ", " + strings[5] + "};");
}
