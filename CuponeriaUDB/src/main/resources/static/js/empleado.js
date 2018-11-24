
var p = {
	
		informacionCupon: document.getElementById("informacionCupon"),
		verificar: document.querySelector("#verificar"),
		codigoCupo: document.querySelector("#codigo"),
		tabla: document.querySelector("#tabla")
}


var m = {
		
		inicio: function(){			
					
				p.verificar.addEventListener("click", m.aparecer);
			
		},
		aparecer: function(event){
			
			if(p.codigoCupo == "" || p.codigoCupo == null){
				p.informacionCupon.style.display = "none";
			}else{	
				p.informacionCupon.style.display = "block";
			}

		}
}

m.inicio();
