import {Customer} from "../models/Customer";
import {Service} from "./Service";

export class CustomerService extends Service{

    constructor() {
        super("/customers");
    }

    public async getCustomers(): Promise<Customer[]> {
        return this.get<Customer>("");
    }

    public async createCustomer(customer: Customer): Promise<Customer> {
        return this.post<Customer>("", customer);
    }

    public async deleteCustomer(id: number) : Promise<string> {
        return this.delete(`/${id}`);
    }
}