console.log("Contacts.js");

const baseURL="http://localhost:8081";

const viewContactModal = document.getElementById('view_contact_modal');

// options with default values
const options = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => {
    console.log("modal is hidden");
  },
  onShow: () => {
    setTimeout(() => {
      contactModal.classList.add("scale-100");
    }, 50);
  },
  onToggle: () => {
    console.log("modal has been toggled");
  },
};

// instance options object
const instanceOptions = {
  id: "view_contact_modal",
  override: true,
};

const contactModal=new Modal(viewContactModal,options,instanceOptions);

function openContactModal(){
    contactModal.show();
}

function closeContactModal(){
    contactModal.hide();
}

async function loadContactdata(id) {
  //function call to load data
  console.log(id);
  try {
    const data = await (await fetch(`${baseURL}/api/contacts/${id}`)).json();
    console.log(data);
    document.querySelector("#contact_name").innerHTML = data.name;
    document.querySelector("#contact_email").innerHTML = data.email;
    document.querySelector("#contact_image").src = data.picture;
    document.querySelector("#contact_address").innerHTML = data.address;
    document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
    document.querySelector("#contact_about").innerHTML = data.description;
    const contactFavorite = document.querySelector("#contact_favorite");
    if (data.favorite) {
      contactFavorite.innerHTML =
        "<i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i>";
    } else {
      contactFavorite.innerHTML = "Not Favorite Contact";
    }

    document.querySelector("#contact_website").href = data.websiteLink;
    document.querySelector("#contact_website").innerHTML = data.websiteLink;
    document.querySelector("#contact_linkedIn").href = data.linkedInLink;
    document.querySelector("#contact_linkedIn").innerHTML = data.linkedInLink;
    openContactModal();
  } catch (error) {
    console.log("Error: ", error);
  }
}

// delete contact

async function deleteContact(id) {
  // Detect current theme
  const isDarkTheme = document.documentElement.classList.contains('dark');
  
  Swal.fire({
    title: "Do you want to delete the contact?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Delete",
    cancelButtonText: "Cancel",
    background: isDarkTheme ? "#1f2937" : "#ffffff",
    color: isDarkTheme ? "#ffffff" : "#111827",
    didOpen: (modal) => {
      // Style the title
      const titleElement = modal.querySelector(".swal2-title");
      if (titleElement) {
        titleElement.style.color = isDarkTheme ? "#ffffff" : "#111827";
        titleElement.style.fontSize = "1.25rem";
        titleElement.style.margin = "1rem 0";
      }
      
      // Style the icon
      const iconElement = modal.querySelector(".swal2-icon");
      if (iconElement) {
        iconElement.style.color = "#f59e0b";
      }
      
      // Style the buttons container
      const buttonsContainer = modal.querySelector(".swal2-actions");
      if (buttonsContainer) {
        buttonsContainer.style.gap = "0.75rem";
      }
      
      // Style Delete button (confirm)
      const confirmBtn = modal.querySelector(".swal2-confirm");
      if (confirmBtn) {
        confirmBtn.style.backgroundColor = "#dc2626";
        confirmBtn.style.color = "#ffffff";
        confirmBtn.style.fontWeight = "600";
        confirmBtn.style.padding = "0.75rem 1.5rem";
        confirmBtn.style.fontSize = "1rem";
        confirmBtn.style.border = "2px solid #dc2626";
        confirmBtn.style.borderRadius = "0.5rem";
        confirmBtn.style.cursor = "pointer";
        confirmBtn.style.transition = "all 0.2s ease";
        confirmBtn.addEventListener('mouseenter', function() {
          this.style.backgroundColor = "#991b1b";
          this.style.borderColor = "#991b1b";
        });
        confirmBtn.addEventListener('mouseleave', function() {
          this.style.backgroundColor = "#dc2626";
          this.style.borderColor = "#dc2626";
        });
      }
      
      // Style Cancel button
      const cancelBtn = modal.querySelector(".swal2-cancel");
      if (cancelBtn) {
        cancelBtn.style.backgroundColor = isDarkTheme ? "#4b5563" : "#e5e7eb";
        cancelBtn.style.color = isDarkTheme ? "#ffffff" : "#111827";
        cancelBtn.style.fontWeight = "600";
        cancelBtn.style.padding = "0.75rem 1.5rem";
        cancelBtn.style.fontSize = "1rem";
        cancelBtn.style.border = `2px solid ${isDarkTheme ? "#4b5563" : "#e5e7eb"}`;
        cancelBtn.style.borderRadius = "0.5rem";
        cancelBtn.style.cursor = "pointer";
        cancelBtn.style.transition = "all 0.2s ease";
        cancelBtn.addEventListener('mouseenter', function() {
          this.style.backgroundColor = isDarkTheme ? "#6b7280" : "#d1d5db";
          this.style.borderColor = isDarkTheme ? "#6b7280" : "#d1d5db";
        });
        cancelBtn.addEventListener('mouseleave', function() {
          this.style.backgroundColor = isDarkTheme ? "#4b5563" : "#e5e7eb";
          this.style.borderColor = isDarkTheme ? "#4b5563" : "#e5e7eb";
        });
      }
    }
  }).then((result) => {
    /* Read more about isConfirmed, isDenied below */
    if (result.isConfirmed) {
      const url = `${baseURL}/user/contacts/delete/` + id;
      window.location.replace(url);
    }
  });
}