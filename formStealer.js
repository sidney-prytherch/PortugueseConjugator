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


let table = `Infinitive
Impersonal	fazer
Personal	fazer	fazeres	fazer	fazermos	fazerdes	fazerem
Gerund
fazendo
Past participle
Masculine	feito	feitos
Feminine	feita	feitas
Indicative
Present	faço	fazes	faz	fazemos	fazeis	fazem
Imperfect	fazia	fazias	fazia	fazíamos	fazíeis	faziam
Preterite	fiz	fizeste	fez	fizemos	fizestes	fizeram
Pluperfect	fizera	fizeras	fizera	fizéramos	fizéreis	fizeram
Future	farei	farás	fará	faremos	fareis	farão
Conditional
faria	farias	faria	faríamos	faríeis	fariam
Subjunctive
Present	faça	faças	faça	façamos	façais	façam
Imperfect	fizesse	fizesses	fizesse	fizéssemos	fizésseis	fizessem
Future	couber	couberes	couber	coubermos	couberdes	couberem
Imperative
Affirmative	-	cabe	caiba	caibamos	cabei	caibam
Negative (não)	-	caibas	caiba	caibamos	caibais	caibam`;

let infinitive = table.match(/(?<=Impersonal\t)(\S*)/g)[0].trim();
let persInfs = table.match(/(?<=Personal\t)([\S\t]*)/g)[0].trim().split("\t");
let gerund = table.match(/(?<=Gerund\n)(\S*)/g)[0].trim();
let participle = table.match(/(?<=Past participle\nMasculine\t)(\S*)/g)[0].trim();

let preses = table.match(/(?<=Present\t)(.*)/g);
let imperfs = table.match(/(?<=Imperfect\t)(.*)/g);
let futs = table.match(/(?<=Future\t)(.*)/g);

let presInd = preses[0].trim().split("\t");
let impInd = imperfs[0].trim().split("\t");
let pretInd = table.match(/(?<=Preterite\t)(.*)/g)[0].trim().split("\t");
let plupInd = table.match(/(?<=Pluperfect\t)(.*)/g)[0].trim().split("\t");
let futInd = futs[0].trim().split("\t");
let cond = table.match(/(?<=Conditional\n)(.*)/g)[0].trim().split("\t");

let presSubj = preses[1].trim().split("\t");
let impSubj = imperfs[1].trim().split("\t");
let futSubj = futs[1].trim().split("\t");

let impAff = table.match(/(?<=Affirmative\t)(.*)/g)[0].trim().split("\t")
let impAff = table.match(/(?<=Negative \(não\)\t)(.*)/g)[0].trim().split("\t")
