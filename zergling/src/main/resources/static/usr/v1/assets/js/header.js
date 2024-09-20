document.addEventListener('DOMContentLoaded', function () {

    /*스크롤 헤더*/
    var prevScrollpos = window.scrollY; 
     
    if(prevScrollpos > 0){
        // document.querySelector(".customer_service_area").style.top = "-48px";
        // document.querySelector(".customer_service_area").style.maxHeight ="0";
        // document.querySelector(".customer_service_area").style.position = "absolute";
        // document.querySelector(".customer_service_area").style.padding ="0";
        // document.querySelector(".customer_service_area").style.visibility = "hidden";
        document.querySelector(".customer_service_area").classList.add('hidden');
        document.querySelector("header").style.position = "fixed";
    } 


    window.onscroll = function() { 
        var currentScrollpos = window.scrollY;
        
        
        // if (prevScrollpos > currentScrollpos) { 
        //     // 네비가 보임
        //     // document.querySelector("header").style.top = "0"; 
        //     // document.querySelector("header").style.paddingTop = "20px"; 
            
        // } else { 
        //     // 네비가 안보임
        //     // document.querySelector("header").style.top = "-225px"; 
        // }
        
        if(currentScrollpos <= 300){
            document.querySelector("header").style.position = "relative";
            document.querySelector("main").style.paddingTop = "0";
            
        } else{
            document.querySelector("header").style.position = "fixed";
            document.querySelector("main").style.paddingTop = "196px";
        }

        if(currentScrollpos <= 300){
            // document.querySelector(".customer_service_area").style.position = "relative";
            // document.querySelector(".customer_service_area").style.top ="0";
            // document.querySelector(".customer_service_area").style.maxHeight ="none";
            // document.querySelector(".customer_service_area").style.padding ="10px";
            // document.querySelector(".customer_service_area").style.visibility = "visible";
            document.querySelector(".customer_service_area").classList.remove('hidden');
        } else{
            // document.querySelector(".customer_service_area").style.top = "-48px";
            // document.querySelector(".customer_service_area").style.maxHeight ="0";
            // document.querySelector(".customer_service_area").style.position = "absolute";
            document.querySelector(".customer_service_area").classList.add('hidden');
            // document.querySelector(".customer_service_area").style.padding ="0";
            
        }
        
        if(currentScrollpos <= 300){
            document.querySelector(".scrolltable").classList.remove('active');
            document.querySelector(".gnb_search").classList.remove('active');
        } else{
            // console.log(window.scrollY);
            // console.log("currentScrollpos: " +currentScrollpos );
            document.querySelector(".scrolltable").classList.add('active');
            document.querySelector(".gnb_search").classList.add('active');
            
        }
        prevScrollpos = currentScrollpos; 
    }



});