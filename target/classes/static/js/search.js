
function funFilter(){
    let filter = document.getElementById("filter").value.trim();
    let tr = document.getElementsByTagName("tr");
    for(let i = 1; i <tr.length; i++){
        let td = tr[i].children[2].textContent;
        tr[i].style.display = "";
        if(td.toUpperCase().indexOf(filter.toUpperCase()) == -1){
            tr[i].style.display = "none";
        }
        else{
            tr[i].style.display = "";
        }
    }
}
function validateDelete(){
    
    let size = document.getElementById("size").innerText;
    if(size.trim() == 0 || size.trim() == ""){
        event.preventDefault();
    }
}
function validatePath(){
    
    let path = document.getElementById("path").value;
    if(path.trim() == ""){
        document.getElementById("path").value = "";
        document.getElementById("val-path").setAttribute("class","col-6 was-validated")
        document.getElementById("path").setAttribute("required",true);
        document.getElementById("path").setAttribute("title","Please provide valid path");
        event.preventDefault();
    }
}
