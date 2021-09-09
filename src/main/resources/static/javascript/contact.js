import {AJAX, showFormMessage} from "./ajax.js";
import {ContactUsMessage} from "./ContactUsMessage.js";

window.addEventListener('load', e => {
    document.querySelector('#contact-us').classList.add('nav-item--selected');
});

document.querySelector('#submit-msg').addEventListener('click', async () => {
    const formEl = document.querySelector('.form')
    const name = document.querySelector('#input-name').value;
    const email = document.querySelector('#input-email').value;
    const msg = document.querySelector('#input-msg').value;

    if (!(name && email && msg)) {
        showFormMessage('please fill in all fields', false, formEl);
        return;
    }

    const post = await AJAX('/message', new ContactUsMessage(name, email, msg));
    showFormMessage('Thank you. Your message has been sent and we will be in touch shortly', true, formEl);
    setTimeout(function() {
        window.location.href = '/';
    }, 2000);


})
