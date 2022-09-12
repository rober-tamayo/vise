class ProductManager{
	tableElement = null;
	filtroBrand = null;
	btnSearch = null;
	filtroPrice = null;
	btnSearchPri = null;
	generaList = [];
	
	async searchAll(){
		const respuesta = await fetch('/getProducts')/*.then(response => response.json()).then(data => data)*/;
		const respData = await respuesta.json();
		this.llenaTabla(respData);
	}
	
	async searchBrand(brand){
		const respuesta = await fetch( `/getProducts/${brand}`)/*.then(response => response.json()).then(data => data)*/;
		const respData = await respuesta.json();
		this.llenaTabla(respData);
	}
	
	async searchPrice(price){
		const respuesta = await fetch( `/getProducts/byPrice/${price}`)/*.then(response => response.json()).then(data => data)*/;
		const respData = await respuesta.json();
		this.llenaTabla(respData);
	}
	
	
	constructor(){
		this.tableElement = document.getElementById('contenido');
		this.filtroBrand = document.getElementById('searchBrand');
		this.btnSearch = document.getElementById('btnSearch');
		this.btnSearch.addEventListener('click',this.searchHandlerBrand);
		this.filtroPrice = document.getElementById('searchPrice');
		this.btnSearchPri = document.getElementById('btnSearchPri');
		this.btnSearchPri.addEventListener('click',this.searchHandlerPrice);	
		
		this.searchAll();
	}
	
	searchHandlerBrand = (evento) =>{
		console.log(this.filtroBrand.value);
		this.tableElement.innerHTML = ``;
		this.searchBrand(this.filtroBrand.value);
	}
	
	searchHandlerPrice = (evento) =>{
		console.log(this.filtroPrice.value);
		this.tableElement.innerHTML = ``;
		this.searchPrice(this.filtroPrice.value);
	}
	
	llenaTabla(productos){
		console.log(productos,this.tableElement)
		productos.forEach(prod => {
			this.tableElement.innerHTML += `
				<tr class="listProductos" brand="${prod.brand}" id="producto${prod.id}">
					<td>${prod.id}</td>
					<td>${prod.name}</td>
					<td>${prod.brand}</td>
					<td>${prod.madein}</td>
					<td>\$${prod.price}</td>
					<td>
						<a type="button" class="btn btn-success" href="/edit/${prod.id}">Edit</a>
	                	&nbsp;&nbsp;&nbsp;
	                	<a type="button" class="btn btn-danger" href="/delete/${prod.id}">Delete</a>
                	</td>
				</tr>
			`;
		});
	}
}


let d = new ProductManager();

