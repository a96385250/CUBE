function monthFormat(dateTime){
    var Month = dateTime.getMonth()+1;
    return (Month.toString().length == 2 ? Month :  "0" + Month.toString());
}

function dateFormat(dateTime){
    var date = dateTime.getDate();
    return (date.toString().length == 2 ? date : "0" + date.toString());
}

function timeFormat(dateTime){
    var hours = dateTime.getHours().toString();
    var minutes = dateTime.getMinutes().toString();

    return (hours.length==2? hours : "0" + hours) + ":" + (minutes.length==2? minutes : "0" + minutes);
}