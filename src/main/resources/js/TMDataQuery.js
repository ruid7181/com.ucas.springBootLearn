function to_json(){

    let path = document.getElementById("path").value.toString();
    let row = document.getElementById("row").value.toString();
    let t_start = document.getElementById("t_start").value.toString();
    let t_end = document.getElementById("t_end").value.toString();
    let t_list = document.getElementById("t_lis").value.toString();


    let data = {};
    data['con_path'] = path;
    data['con_row'] = row;
    if (t_start !== '' && t_end !== '' && t_list === '') {
        data['time'] = "on";
        data['con_tRange'] = t_start.split('-').join('') + "|" + t_end.split('-').join('');
        data['con_tLis'] = "false";
        window.alert("if1");
    } else if (t_list !== '' && t_start === '' && t_end === '') {
        data['time'] = "on";
        data['con_tRange'] = "false";
        data['con_tLis'] = t_list.split(',').join('|');
        window.alert("else if 2");
    } else if (t_list !== '' && t_start !== '' && t_end !== '') {
        data['time'] = "on";
        data['con_tRange'] = t_start.split('-').join('') + "|" + t_end.split('-').join('');
        data['con_tLis'] = t_list.split(',').join('|');
        window.alert("else if 3");
    } else {
        data['time'] = "off";
    }

    window.alert(JSON.stringify(data));

    $.get({
            url:'/data/jpaCustomQuery',
            type:'POST',
            contentType:'application/json',
            dataType:'json',
            data:JSON.stringify(data),
            success: function (result){
                $('.table').empty();
                let tableHead = "<tr>" +
                    "<th>id</th>" +
                    "<th>卫星</th>" +
                    "</tr>"
                $('.table').append(tableHead);
                let item;
                $.each(result, function(index, element) {
                    item="<tr><td>"+element['f_name']+"</td><td>"+element['sat']+"</td></tr>";
                    $('.table').append(item);
                });
            },
            error: function (){
                window.alert('error')
            }
        }
    );
}