export class ContactUsMessage {
    name;
    email;
    subject;
    message;

    constructor(name, email, subject, message) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }
}