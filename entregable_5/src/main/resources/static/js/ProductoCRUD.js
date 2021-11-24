"use strict";
document.addEventListener('DOMContentLoaded', cargarPagina);

function cargarPagina () {
    let lista = document.createElement("ul");
    lista.className = "lista-productos";
    document.getElementById("formulario-producto").appendChild(lista);
    getProductos();

    document.getElementById("post-producto").addEventListener("click", function(e) {

        e.preventDefault();

        let nombre = document.getElementById("nombre-producto").value;
        let precio = document.getElementById("precio-producto").value;

    //OBJETO
        let producto = { 
            "nombre": nombre,
            "precio": precio,
        }

        
        console.log(producto);
        if(producto.nombre != "" && producto.precio != "" && producto.precio > 0){  //SI ESTAN VACIOS LOS CAMPOS NO SE ENVIA
            fetch('http://localhost:8080/productos', {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(producto)
            })
                .then(response =>  response.json())
                .then( () => getProductos()) 
                .catch(error => console.log(error));

        }

        document.getElementById("formulario-producto").reset();  //SE RESETEAN LOS CAMPOS DEL FORMULARIO
    });

    
    function getProductos(){
        let lista = document.querySelector(".lista-productos"); 
        lista.innerHTML = ""; //SE VACIA EL DIV
        fetch('productos')
            .then(response =>  response.json())
            .then(producto => renderProductos(producto)) //SE LLAMA A LA FUNCION QUE LOS MUESTRA
            .catch(error => console.log(error));
    }
    
    function renderProductos(productos) {
        productos.forEach(producto => {
                lista.innerHTML += "<li class='producto' id=producto-"+ producto.id+">" + producto.nombre +" "+ producto.precio+ "<button class='botonEditar' id="+ producto.id+">Editar</button><button class='botonEliminarProducto' id="+ producto.id+">Eliminar</button></li>";
                boton_editar_producto(); //se le da funcionalidad
                boton_borrar_producto(); //se le da funcionalidad
        });
    }
    
    function boton_borrar_producto () { 
        let buttons = document.getElementsByClassName('botonEliminarProducto');
        for (let i = 0; i < buttons.length; i++) {
            buttons[i].addEventListener('click', function() {
                let id = buttons[i].id;
                borrarproducto_en_servidor(id);
            })
            }
    }
    
    function borrarproducto_en_servidor(id) {
        fetch('productos/'+ id,{
            'method': 'DELETE',
            'mode': "cors"
        })
        .then(response =>  response.json())
        .then(get => getproductos()) 
        .catch(error => console.log(error));
    }

    
    function boton_editar_producto () { 
        let buttons = document.getElementsByClassName('botonEditar'); 
        for (let i = 0; i < buttons.length; i++) {
            buttons[i].addEventListener('click', function() {
                    let id = buttons[i].id;
                    createFormEdit(id);
                })
                
            }
    }


    function createFormEdit(id){
        let producto_id = "producto-" + id;
        let li = document.getElementById(producto_id);
        li.innerHTML = '<form id="formulario-producto-edit"> <input type="text" placeholder="Nombre" id="nombre-editado"> <input type="text" placeholder="Apellido" id="apellido-editado"> <input type="number" placeholder="dni" id="dni-editado"> <button id="editar-'+ id +'">Editar</button></form>';
        let btn_id = "#editar-"+ id;
        let btn = document.querySelector(btn_id)
        btn.addEventListener("click", function() {editarComentario_en_servidor(id);})
    }


    function editarComentario_en_servidor(id) {
        let producto = { 
            "nombre": document.getElementById("nombre-editado").value,
            "apellido": document.getElementById("apellido-editado").value,
            "dni": document.getElementById("dni-editado").value,
            "id": id
        }
        console.log(producto);
    
        fetch('productos', {
            method: 'PUT',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(producto)
        })
        .then(response =>  response.json())
        .then(() => getproductos())
        .catch(error => console.log(error));
    }

}