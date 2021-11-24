export class Service {
    protected baseUrl = "http://localhost:8080/";

    constructor(endpoint: string) {
        this.baseUrl += endpoint;
    }

    protected async get<T> (endpoint: string) {
        this.baseUrl += endpoint;
        const respose = await fetch(this.baseUrl)
            .then(res => res.json());
        return respose;
    }
}