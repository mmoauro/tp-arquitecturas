import React from 'react';
import {CustomerService} from "../services/CustomerService";
import {Customer} from "../models/Customer";
import {ProductService} from "../services/ProductService";
import {Product} from "../models/Productt";

interface Props {

}

interface State {
    customers: Customer[];
    products: Product[];
}

export class App extends React.Component<Props, State> {
    private customerService: CustomerService = new CustomerService();
    private productService: ProductService = new ProductService();

    async componentDidMount() {
        const customers = await this.customerService.getCustomers();
        const products = await this.productService.getProducts();
        this.setState({customers: customers, products: products});
    }

    render() {
    return (
        <>
            {this.state?.customers.map(customer => {
                return (
                    <h1>{customer.name}</h1>
                )
            })}
            {this.state?.products.map(product => {
                return (
                    <h1>{product.price}</h1>
                )
            })}
        </>
    );
  }
}
