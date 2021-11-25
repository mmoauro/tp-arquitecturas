import React from 'react';
import {Button, Col, Row} from "react-bootstrap";
import {Product} from "../models/Productt";
import {ProductService} from "../services/ProductService";

interface Props {
    addProductToList: (product: Product) => void;
}

interface State {
    product: Product;
}

export class CreateProductForm extends React.Component<Props, State> {
    private productService: ProductService = new ProductService();

    constructor(props: Props) {
        super(props);
        this.state = {
            product: {
                name: "",
                price: 0,
        }}
    }

    submitForm(e: any) {
        e.preventDefault()
        this.productService.createProduct(this.state.product)
            .then((product: Product) => {
                product.id && this.props.addProductToList(product);
            });
    }

    handleInputChange(key: keyof Product, value: string) {
        const product = {...this.state.product}
        // @ts-ignore
        product[key] = value;
        this.setState({product: product});
    }

    render() {
        return (
            <>
                <h4>Create product</h4>
                <div>
                    <form onSubmit={(e) => this.submitForm(e)}>
                        <Row className={"mt-2"}>
                            <Col md={4}>
                                <input type="text"
                                       value={this.state.product.name}
                                       placeholder={"Name"}
                                       onChange={(e:any) => this.handleInputChange("name", e.target.value)}
                                />
                            </Col>
                            <Col md={4}>
                                <input type="number"
                                       value={this.state.product.price}
                                       placeholder={"Surname"}
                                       onChange={(e:any) => this.handleInputChange("price", e.target.value)}
                                />
                            </Col>
                        </Row>
                        <Row className={"mt-2"}>
                            <Col md={6}>
                                <Button type={"submit"}>Create</Button>
                            </Col>
                        </Row>
                    </form>
                </div>
            </>
        );
    }
}
