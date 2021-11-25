import React from 'react';
import {CustomerService} from "../services/CustomerService";
import {Customer} from "../models/Customer";
import {Button, Col, Row} from "react-bootstrap";

interface Props {
    addCustomerToList: (customer: Customer) => void;
}

interface State {
    customer: Customer;
}

export class CreateCustomerForm extends React.Component<Props, State> {
    private customerService: CustomerService = new CustomerService();

    constructor(props: Props) {
        super(props);
        this.state = {customer: {
            name: "",
            surname: "",
            dni: "",
        }}
    }

    submitForm(e: any) {
        e.preventDefault()
        this.customerService.createCustomer(this.state.customer)
            .then(customer => {
                customer.id && this.props.addCustomerToList(customer)
            })
    }

    handleInputChange(key: keyof Customer, value: string) {
        const customer = {...this.state.customer}
        // @ts-ignore
        customer[key] = value;
        this.setState({customer: customer});
    }

    render() {
        return (
            <>
                <h4>Create customer</h4>
                <div>
                    <form onSubmit={(e) => this.submitForm(e)}>
                        <Row className={"mt-2"}>
                            <Col md={4}>
                                <input type="text"
                                       value={this.state.customer.name}
                                       placeholder={"Name"}
                                       onChange={(e:any) => this.handleInputChange("name", e.target.value)}
                                />
                            </Col>
                            <Col md={4}>
                                <input type="text"
                                       value={this.state.customer.surname}
                                       placeholder={"Surname"}
                                       onChange={(e:any) => this.handleInputChange("surname", e.target.value)}
                                />
                            </Col>
                        </Row>
                        <Row className={"mt-2"}>
                            <Col md={6}>
                                <input type="number"
                                       value={this.state.customer.dni}
                                       placeholder={"Dni"}
                                       onChange={(e:any) => this.handleInputChange("dni", e.target.value)}
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
