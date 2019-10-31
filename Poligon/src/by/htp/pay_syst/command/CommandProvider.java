package by.htp.pay_syst.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.pay_syst.command.impl.AccountRefill;
import by.htp.pay_syst.command.impl.AddCard;
import by.htp.pay_syst.command.impl.AddNewNameCard;
import by.htp.pay_syst.command.impl.AuthorizationCommand;
import by.htp.pay_syst.command.impl.CorrectIdentificNumberPassport;
import by.htp.pay_syst.command.impl.CorrectSeriesNumbPassport;
import by.htp.pay_syst.command.impl.CorrectionAddress;
import by.htp.pay_syst.command.impl.CorrectionCodeword;
import by.htp.pay_syst.command.impl.CorrectionLogin;
import by.htp.pay_syst.command.impl.CorrectionName;
import by.htp.pay_syst.command.impl.CorrectionPassword;
import by.htp.pay_syst.command.impl.CorrectionPhoneNumber;
import by.htp.pay_syst.command.impl.CorrectionResidenceRegistration;
import by.htp.pay_syst.command.impl.CorrectionSurname;
import by.htp.pay_syst.command.impl.DeleteNameCard;
import by.htp.pay_syst.command.impl.DeleteUser;
import by.htp.pay_syst.command.impl.ForgetPassword;
import by.htp.pay_syst.command.impl.GetAccountsAndCards;
import by.htp.pay_syst.command.impl.GetAllAccounts;
import by.htp.pay_syst.command.impl.GetAllCards;
import by.htp.pay_syst.command.impl.GetAllNameCard;
import by.htp.pay_syst.command.impl.GetAllUsers;
import by.htp.pay_syst.command.impl.Localization;
import by.htp.pay_syst.command.impl.LockAccount;
import by.htp.pay_syst.command.impl.NoSuchCommand;
import by.htp.pay_syst.command.impl.Payment;
import by.htp.pay_syst.command.impl.Registration;
import by.htp.pay_syst.command.impl.RegistrationAdmin;
import by.htp.pay_syst.command.impl.TransferBetweenUserCards;
import by.htp.pay_syst.command.impl.UnlockAccount;
import by.htp.pay_syst.command.impl.go_to.GoToAdminCorrectionDataUser;
import by.htp.pay_syst.command.impl.go_to.GoToAdminMainAccount;
import by.htp.pay_syst.command.impl.go_to.GoToCorrectData;
import by.htp.pay_syst.command.impl.go_to.GoToCorrectionPassword;
import by.htp.pay_syst.command.impl.go_to.GoToForgetPassword;
import by.htp.pay_syst.command.impl.go_to.GoToLockAccount;
import by.htp.pay_syst.command.impl.go_to.GoToMain;
import by.htp.pay_syst.command.impl.go_to.GoToMainAccount;
import by.htp.pay_syst.command.impl.go_to.GoToMainLoc;
import by.htp.pay_syst.command.impl.go_to.GoToPageAddCard;
import by.htp.pay_syst.command.impl.go_to.GoToPayment;
import by.htp.pay_syst.command.impl.go_to.GoToRefill;
import by.htp.pay_syst.command.impl.go_to.GoToRegistration;
import by.htp.pay_syst.command.impl.go_to.GoToRegistrationAdmin;
import by.htp.pay_syst.command.impl.go_to.GoToTransferBetweenCards;
import by.htp.pay_syst.command.impl.go_to.GoToUnlockAccount;

public class CommandProvider {
	

	private static final CommandProvider instance = new CommandProvider();
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	
	
	public CommandProvider() {
		
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		commands.put(CommandName.ACCOUNT_REFILL, new AccountRefill());
		commands.put(CommandName.PAYMENT, new Payment());
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.ADD_CARD, new AddCard());
		commands.put(CommandName.DELETE_USER, new DeleteUser());
		commands.put(CommandName.REGISTRATION_ADMIN, new RegistrationAdmin());
		commands.put(CommandName.LOCK_ACCOUNT, new LockAccount());
		commands.put(CommandName.UNLOCK_ACCOUNT, new UnlockAccount());
//		commands.put(CommandName.CORRECTION_DATA, new CorrectionData());//------		
		commands.put(CommandName.GET_ACCOUNTS_CARDS, new GetAccountsAndCards());
		
		
		commands.put(CommandName.CORRECTION_LOGIN, new CorrectionLogin());
		commands.put(CommandName.CORRECTION_PASSWORD, new CorrectionPassword());
		commands.put(CommandName.CORRECTION_NAME, new CorrectionName());
		commands.put(CommandName.CORRECTION_SURNAME, new CorrectionSurname());
		commands.put(CommandName.CORRECTION_ADDRESS, new CorrectionAddress());
		commands.put(CommandName.CORRECTION_CODEWORD, new CorrectionCodeword());
		commands.put(CommandName.CORRECTION_PHONE_NUMBER, new CorrectionPhoneNumber());
		commands.put(CommandName.CORRECTION_RESIDENCE_REGISTRATION, new CorrectionResidenceRegistration());
		commands.put(CommandName.FORGET_PASSWORD, new ForgetPassword());
		commands.put(CommandName.TRANSFER_BETWEEN_USERS_CARDS, new TransferBetweenUserCards());
		
		commands.put(CommandName.CORRECT_SERIES_NUMB_PASSPORT, new CorrectSeriesNumbPassport());
		commands.put(CommandName.CORRECT_IDENTIFIC_NUMB_PASSPORT, new CorrectIdentificNumberPassport());
		commands.put(CommandName.GET_ACCOUNTS, new GetAllAccounts());
		commands.put(CommandName.GET_CARDS, new GetAllCards());
		commands.put(CommandName.GET_USERS, new GetAllUsers());
		commands.put(CommandName.ADD_NEW_NAME_CARD, new AddNewNameCard());
		commands.put(CommandName.DELETE_NAME_CARD, new DeleteNameCard());
		commands.put(CommandName.GET_ALL_NAME_CARD, new GetAllNameCard());
		
		commands.put(CommandName.GO_TO_CORRECT_DATA, new GoToCorrectData());
		commands.put(CommandName.GO_TO_LOCK_ACCOUNT, new GoToLockAccount());
		commands.put(CommandName.GO_TO_MAIN, new GoToMain());
		commands.put(CommandName.GO_TO_MAIN_LOC, new GoToMainLoc());
		commands.put(CommandName.GO_TO_MAIN_ACCOUNT, new GoToMainAccount());
		commands.put(CommandName.GO_TO_PAGE_ADD_CARD, new GoToPageAddCard());
		commands.put(CommandName.GO_TO_PAYMENT, new GoToPayment());
		commands.put(CommandName.GO_TO_REFILL, new GoToRefill());
		commands.put(CommandName.GO_TO_REGISTRATION, new GoToRegistration());
		commands.put(CommandName.GO_TO_REGISTRATION_ADMIN, new GoToRegistrationAdmin());
		commands.put(CommandName.GO_TO_UNLOCK_ACCOUNT, new GoToUnlockAccount());
		commands.put(CommandName.GO_TO_ADMIN_CORRECTION_DATA, new GoToAdminCorrectionDataUser());
		commands.put(CommandName.GO_TO_CORRECTION_PASSWORD, new GoToCorrectionPassword());
		commands.put(CommandName.GO_TO_FORGET_PASSWORD, new GoToForgetPassword());
		commands.put(CommandName.GO_TO_TRANSFER_BETWEEN_CARDS, new GoToTransferBetweenCards());
		commands.put(CommandName.GO_TO_ADMIN_MAIN_ACCOUNT, new GoToAdminMainAccount());
		commands.put(CommandName.LOCALIZATION, new Localization());
		
		
				
	}
	
	
	
	public static CommandProvider getInstance() {
		return instance;
	}



	public Command getCommand(String commandName) {
//		System.out.println("provider commandName = " + commandName);
		CommandName name = CommandName.valueOf(commandName.toUpperCase());
//		System.out.println("provider name = " + name);
		Command command;
		if(null!=name){
			command = commands.get(name);
		}else{
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}
		
		return command;
	}
	
	
	
}
