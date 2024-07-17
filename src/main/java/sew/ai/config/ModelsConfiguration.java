package sew.ai.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import sew.ai.helpers.props.FilePaths;
import sew.ai.models.*;
import sew.ai.utils.FileUtil;

import java.io.IOException;
import java.nio.file.Files;

public class ModelsConfiguration {
	public static Customer readCustomerConfig() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/customer.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Customer[] customers = null;
		try {
			customers = objectMapper.readValue(jsonData, Customer[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Customer(customers);
	}

	/**
	 * This methods provides all the banks present in the banks json file.
	 *
	 * @return Bank object
	 */
	public static Bank readBankAccounts() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/bank.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bank[] banks = null;
		try {
			banks = objectMapper.readValue(jsonData, Bank[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Bank(banks);
	}

	/**
	 * This method provides all the cards present in the card json file.
	 *
	 * @return
	 */
	public static Card readCards() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/card.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Card[] cards = null;
		try {
			cards = objectMapper.readValue(jsonData, Card[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Card(cards);
	}

	/**
	 * This method provides all the cards present in the card json file.
	 *
	 * @return
	 */
	public static Outage readOutages() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/outage.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Outage[] outages = null;
		try {
			outages = objectMapper.readValue(jsonData, Outage[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Outage(outages);
	}

	/**
	 * This method provides all the cards present in the card json file.
	 *
	 * @return
	 */
	public static User readUsers() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/user.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		User[] users = null;
		try {
			users = objectMapper.readValue(jsonData, User[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new User(users);
	}

	/**
	 * This method provides all the paypal wallet present in the paypal json file.
	 *
	 * @return
	 */
	public static PayPalWallet readPayPalWallets() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/paypal_wallet.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		PayPalWallet[] payPalWallets = null;
		try {
			payPalWallets = objectMapper.readValue(jsonData, PayPalWallet[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new PayPalWallet(payPalWallets);
	}

	/**
	 * This method provides all the gpay wallet present in the gpay json file.
	 *
	 * @return
	 */
	public static GPayWallet readGPayWallets() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/gpay_wallet.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		GPayWallet[] gPayWallets = null;
		try {
			gPayWallets = objectMapper.readValue(jsonData, GPayWallet[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new GPayWallet(gPayWallets);
	}

	/**
	 * This method provides all the venmo wallets present in the json file.
	 *
	 * @return
	 */
	public static VenmoWallet readVenmoWallets() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/venmo_wallet.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		VenmoWallet[] venmoWallets = null;
		try {
			venmoWallets = objectMapper.readValue(jsonData, VenmoWallet[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new VenmoWallet(venmoWallets);
	}

	/**
	 * This method provides all the apple wallets present in the apple json file.
	 *
	 * @return
	 */
	public static AppleWallet readAppleWallets() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/apple_wallet.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		AppleWallet[] appleWallets = null;
		try {
			appleWallets = objectMapper.readValue(jsonData, AppleWallet[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new AppleWallet(appleWallets);
	}

	/**
	 * This method provides all the apple tokens present in the apple token json file.
	 *
	 * @return
	 */
	public static Token readTokens() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/token.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Token [] tokens = null;
		try {
			tokens = objectMapper.readValue(jsonData, Token[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Token(tokens);
	}

	/**
	 * This method provides all the apple tokens present in the apple token json file.
	 *
	 * @return
	 */
	public static OneTimePayment readOneTimePaymentObject() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/one_time_payment.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		OneTimePayment [] oneTimePaymentObjects = null;
		try {
			oneTimePaymentObjects = objectMapper.readValue(jsonData, OneTimePayment[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new OneTimePayment(oneTimePaymentObjects);
	}

	/**
	 * This method provides all tokenize payments scenarios objects json file.
	 *
	 * @return
	 */
	public static TokenizePayment readTokenizePaymentObject() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/tokenize_payment.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		TokenizePayment [] tokenizePaymentObjects = null;
		try {
			tokenizePaymentObjects = objectMapper.readValue(jsonData, TokenizePayment[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new TokenizePayment(tokenizePaymentObjects);
	}

	public static Autopay readAutoPayByScenario() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/autopay.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Autopay [] autopays = null;
		try {
			autopays = objectMapper.readValue(jsonData, Autopay[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Autopay(autopays);
	}

	/**
	 * This method provides all  guest user information available in json file.
	 *
	 * @return
	 */
	public static GuestUser readGuestUserObject() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		//String file = FilePaths.DATA_CONFIG + "datamodels/guestUser.json";
		String file = FilePaths.DATA_CONFIG + "datamodels/TestGuestUser.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		GuestUser [] guestUserObjects = null;
		try {
			guestUserObjects = objectMapper.readValue(jsonData, GuestUser[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new GuestUser(guestUserObjects);
	}
	
	public static ConnectMe readConnectme() {
        byte[] jsonData = null;
        ObjectMapper objectMapper = new ObjectMapper();
        String file = FilePaths.DATA_CONFIG + "datamodels/connectme.json";
        try {
            jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConnectMe[] connectMe = null;
        try {
        	connectMe = objectMapper.readValue(jsonData, ConnectMe[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ConnectMe(connectMe);
    }

	public static Services readServices() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/service.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Services[] services = null;
		try {
			services = objectMapper.readValue(jsonData, Services[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Services(services);
	}

	public static AdminWaysToSave readAdminWaysToSave() {
		byte[] jsonData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String file = FilePaths.DATA_CONFIG + "datamodels/admin_ways_to_save.json";
		try {
			jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile(file)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		AdminWaysToSave[] adminWaysToSave = null;
		try {
			adminWaysToSave = objectMapper.readValue(jsonData, AdminWaysToSave[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new AdminWaysToSave(adminWaysToSave);
	}
	
	

}
