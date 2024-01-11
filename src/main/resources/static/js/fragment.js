function updateNav() {
    $.ajax({
        type : "post",
        url : "/index",
        dataType : "json",
        success : function(res){
            if(res.count!=null){
                document.querySelector(".newRequest p").innerText=res.count;
            }
        }
    });
    }
    document.addEventListener("DOMContentLoaded", function() {
        updateNav();
    });