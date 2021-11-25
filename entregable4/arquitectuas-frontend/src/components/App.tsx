import React from 'react';
import {CustomerService} from "../services/CustomerService";
import {Customer} from "../models/Customer";
import {ProductService} from "../services/ProductService";
import {Product} from "../models/Productt";
import {Button, Col, Row} from "react-bootstrap";
import {CreateCustomerForm} from "./CreateCustomerForm";
import 'bootstrap/dist/css/bootstrap.min.css';
import {CreateProductForm} from "./CreateProductForm";

interface Props {

}

interface State {
    customers: Customer[];
    products: Product[];
}

export class App extends React.Component<Props, State> {
    private customerService: CustomerService = new CustomerService();
    private productService: ProductService = new ProductService();

    constructor(props: Props) {
        super(props);
        this.state = {
            customers: [],
            products: [],
        }
    }

    async componentDidMount() {
        const customers = await this.customerService.getCustomers();
        const products = await this.productService.getProducts();
        this.setState({customers: customers, products: products});
    }

    addCustomerToList(customer: Customer) {
        const customers = [...this.state.customers];
        customers.push(customer);
        this.setState({customers: customers});
    }

    addProductToList(product: Product) {
        const products = [...this.state.products];
        products.push(product);
        this.setState({products: products});
    }

    deleteCustomer(id?: number) {
        id && this.customerService.deleteCustomer(id)
        // TODO: Check response
        this.setState({customers: this.state.customers.filter(c => c.id !== id)});
    }

    render() {
        return (
            <>
                <Row>
                    <Col md={5} style={{marginLeft: "20px"}}>
                        <h1>Customers</h1>
                        <div style={{width: "50%"}}>
                            {this.state?.customers.map(customer => {
                                return (
                                    <Row key={customer.id} className={"mt-2"} style={{border: "1px solid black"}}>
                                        <Col md={6}>
                                            <p>Name: {customer.name}</p>
                                        </Col>
                                        <Col md={6}>
                                            <p>Surname: {customer.surname}</p>
                                        </Col>
                                        <Col md={6}>
                                            <p>Dni: {customer.dni}</p>
                                        </Col>
                                        <Col md={6}>
                                            <Button onClick={() => this.deleteCustomer(customer.id)}>Delete</Button>
                                        </Col>
                                    </Row>
                                )
                            })}
                        </div>
                        <CreateCustomerForm
                            addCustomerToList={(customer: Customer) => this.addCustomerToList(customer)}
                        />
                    </Col>
                    <Col md={5}>
                        <div style={{width: "50%"}}>
                            <h1>Products</h1>
                            {this.state?.products.map(product => {
                                return (
                                    <Row key={product.id} className={"mt-2"} style={{border: "1px solid black"}}>
                                        <Col md={6}>
                                            <p>Name: {product.name}</p>
                                        </Col>
                                        <Col md={6}>
                                            <p>Price: ${product.price}</p>
                                        </Col>
                                    </Row>
                                )
                            })}

                        </div>
                        <CreateProductForm
                            addProductToList={(product: Product) => this.addProductToList(product)}
                        />
                    </Col>
                </Row>
            </>
        );
    }
}
